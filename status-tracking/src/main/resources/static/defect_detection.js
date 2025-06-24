document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("detectDefectsBtn").addEventListener("click", function () {
        let fileInput = document.getElementById("imageInput").files;

        if (fileInput.length === 0) {
            alert("Please upload at least one image.");
            return;
        }

        let formData = new FormData();
        for (let i = 0; i < fileInput.length; i++) {
            formData.append("files", fileInput[i]);  // ✅ Append multiple files
        }

        fetch("http://localhost:8000/predict/", {
            method: "POST",
            body: formData
        })
        .then(response => response.json())
        .then(data => {
            console.log("API Response:", data); // Debugging: Log API response
            displayDefects(data.results); // ✅ Handle multiple results
        })
        .catch(error => console.error("Error detecting defects:", error));
    });
});

function displayDefects(results) {
    let resultContainer = document.getElementById("resultContainer");
    resultContainer.innerHTML = ""; // ✅ Clear previous results

    results.forEach(data => {
        let outputImagePath = `http://localhost:8000${data.output_image}?timestamp=${new Date().getTime()}`;

        // ✅ Check if NON_DEFECTIVE is detected
        let isNonDefective = data.detections.length === 1 && data.detections[0].class_name === "NON_DEFECTIVE";

        let resultHTML = `<h3>${data.filename}</h3>`;

        if (isNonDefective) {
            resultHTML += "<h3 style='color:green;'>✅ Product is Non-Defective</h3>";
        } else {
            let defectList = "<h3 style='color:red;'>❌ Product is Defective</h3><ul>";
            data.detections.forEach(defect => {
                defectList += `<li>Type: <strong>${defect.class_name}</strong> | Confidence: <strong>${(defect.confidence * 100).toFixed(2)}%</strong></li>`;
            });
            defectList += "</ul>";
            resultHTML += defectList;
        }

        resultHTML += `<img src="${outputImagePath}" alt="Detected Image" style="max-width: 100%; margin-top: 10px;">`;

        let resultDiv = document.createElement("div");
        resultDiv.innerHTML = resultHTML;
        resultContainer.appendChild(resultDiv);
    });
}
