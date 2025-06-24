# ... (existing imports and code unchanged)
from fastapi import FastAPI, UploadFile, File, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from fastapi.staticfiles import StaticFiles
from pydantic import BaseModel
from typing import List
import torch
import mysql.connector
from ultralytics import YOLO
import os
import shutil
import logging
import uuid

# Configure logging
logging.basicConfig(level=logging.INFO)

# Load the YOLOv8 model
model_path = os.path.join(os.path.dirname(__file__), "best.pt")
if not os.path.exists(model_path):
    raise FileNotFoundError(f"Model file 'best.pt' not found at {model_path}")

model = YOLO(model_path)

CLASS_NAMES = ["Crack", "Cutting", "Inner-Stamped", "NON_DEFECTIVE", "Outer-Stamped"]

db_config = {
    "host": "localhost",
    "user": "root",
    "password": "root",
    "database": "production_tracking"
}

def store_detection_result(image_id, detections, image_path): 
    try:
        conn = mysql.connector.connect(**db_config)
        cursor = conn.cursor()
        defect_types = ", ".join([d["class_name"] for d in detections])
        confidence_scores = ", ".join([str(d["confidence"]) for d in detections])
        bounding_boxes = ", ".join([f"{bb[0]},{bb[1]},{bb[2]},{bb[3]}" for d in detections for bb in [d["bounding_box"]]])
        query = """INSERT INTO defect_results(image_id,defect_type,confidence,bounding_boxes,image_path) VALUES (%s,%s,%s,%s,%s)"""
        cursor.execute(query, (image_id, defect_types, confidence_scores, bounding_boxes, image_path))
        conn.commit()
        logging.info(f"Stored Result: {image_id},{defect_types},{confidence_scores},{bounding_boxes},{image_path}")
        cursor.close()
        conn.close()
    except Exception as e:
        logging.error(f"Error Storing defect results in MYSQL: {e}")

# FastAPI app
app = FastAPI()
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/")
def root():
    return {"message": "Defect detection API is running"}

@app.post("/predict/")
async def predict(files: List[UploadFile] = File(...)):
    try:
        os.makedirs("uploads", exist_ok=True)
        os.makedirs("outputs/detections", exist_ok=True)

        results_list = []

        for file in files:
            unique_filename = f"{os.path.splitext(file.filename)[0]}_{uuid.uuid4().hex[:8]}.jpg"
            image_path = os.path.join("uploads", unique_filename)
            output_image_path = os.path.join("outputs/detections", unique_filename)

            with open(image_path, "wb") as buffer:
                shutil.copyfileobj(file.file, buffer)

            logging.info(f"Processing image: {image_path}")

            results = model(image_path, save=True, project="outputs", name="detections", exist_ok=True)

            if not os.path.exists(output_image_path):
                raise HTTPException(status_code=500, detail=f"Processed image not found for {file.filename}")

            detections = []
            if results[0].boxes is not None and len(results[0].boxes) > 0:
                detections = [
                    {
                        "class_id": int(box.cls),
                        "class_name": CLASS_NAMES[int(box.cls)],
                        "confidence": round(float(box.conf), 2),
                        "bounding_box": [int(x) for x in box.xyxy[0].tolist()]
                    }
                    for box in results[0].boxes
                ]

            store_detection_result(unique_filename, detections, output_image_path)

            results_list.append({
                "filename": file.filename,
                "total_defects": len(detections),
                "detections": detections,
                "output_image": f"/outputs/detections/{unique_filename}"
            })

        return {"results": results_list}

    except Exception as e:
        logging.error(f"Error processing images: {str(e)}")
        raise HTTPException(status_code=500, detail=f"Error processing images: {str(e)}")

# Serve static files
app.mount("/outputs", StaticFiles(directory="outputs"), name="outputs")

# -------------------- ✅ New Endpoint --------------------

class ProjectRequest(BaseModel):
    trpNumber: str

@app.post("/detect_project")
async def detect_project(request: ProjectRequest):
    trp = request.trpNumber
    folder_path = os.path.join("project_parts", trp)
    os.makedirs("outputs/detections", exist_ok=True)

    if not os.path.exists(folder_path):
        raise HTTPException(status_code=404, detail=f"No folder found for TRP: {trp}")

    results_list = []

    for filename in os.listdir(folder_path):
        if filename.lower().endswith((".jpg", ".jpeg", ".png")):
            image_path = os.path.join(folder_path, filename)
            unique_filename = f"{trp}_{filename}"
            output_image_path = os.path.join("outputs/detections", unique_filename)

            logging.info(f"Processing TRP {trp} image: {image_path}")

            results = model(image_path, save=True, project="outputs", name="detections", exist_ok=True)

            detections = []
            if results[0].boxes is not None and len(results[0].boxes) > 0:
                detections = [
                    {
                        "class_id": int(box.cls),
                        "class_name": CLASS_NAMES[int(box.cls)],
                        "confidence": round(float(box.conf), 2),
                        "bounding_box": [int(x) for x in box.xyxy[0].tolist()]
                    }
                    for box in results[0].boxes
                ]

            store_detection_result(unique_filename, detections, output_image_path)

            results_list.append({
                "filename": filename,
                "total_defects": len(detections),
                "detections": detections,
                "output_image": f"/outputs/detections/{unique_filename}"
            })

    return {"message": f"Detection completed for project {trp}", "results": results_list}
