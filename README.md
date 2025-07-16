# STATUS-TRACKING-DEFECT-DETECTION

**Transforming Quality Monitoring Into Actionable Insights**

![GitHub Last Commit](https://img.shields.io/github/last-commit/Asheyy-17/status-tracking-defect-detection)

---

## Built With:

![Java](https://img.shields.io/badge/Backend-Java-blue?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Framework-SpringBoot-brightgreen?logo=spring)
![FastAPI](https://img.shields.io/badge/API-FastAPI-009688?logo=fastapi)
![YOLOv8](https://img.shields.io/badge/ML-YOLOv8-purple?logo=python)
![MySQL](https://img.shields.io/badge/Database-MySQL-00758F?logo=mysql)

---

## 📑 Table of Contents

- [Overview](#overview)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Usage](#usage)
  - [Testing](#testing)

---

## 📌 Overview

**Status Tracking Defect Detection** is a versatile developer tool that combines a Spring Boot–based backend with integrated Python services for defect analysis and status monitoring.  
It enables scalable management of production data, project statuses, and defect results within a cohesive architecture.

### 🔍 Why status-tracking-defect-detection?

This project streamlines the development of real-time status tracking and defect detection systems. The core features include:

- 🧠 **Image-based Defect Detection**:  
  Integrates **YOLOv8** for automated defect analysis, supporting batch processing and detailed predictions.

- 🔁 **Seamless Backend Integration**:  
  Combines **Spring Boot** with **FastAPI** for efficient communication and API access.

- 🗂 **Data Management**:  
  Manages repositories and services for production, defect, project, and user data — ensuring data consistency.

- 🔐 **Flexible Security & CORS**:  
  Simplifies security configurations for development and testing environments.

- 📊 **Real-time Monitoring**:  
  Offers dashboards and APIs for live project status, delays, and priority tracking.

---

## 🚀 Getting Started

### ✅ Prerequisites

Make sure you have the following installed:

- **Programming Language**: Java  
- **Package Manager**: Maven

---

### 📥 Installation

To build and run the project locally:

```bash
# 1. Clone the repository
git clone https://github.com/Asheyy-17/status-tracking-defect-detection

# 2. Navigate to the project directory
cd status-tracking-defect-detection

# 3. Install the Java backend dependencies using Maven
mvn install

# 4. (Optional) Configure the application properties
# Open the file: src/main/resources/application.properties
# And edit the following:
# spring.datasource.url=jdbc:mysql://localhost:3306/status_tracking_db
# spring.datasource.username=root
# spring.datasource.password=your_password
# spring.jpa.hibernate.ddl-auto=update

# 5. Run the Spring Boot application
mvn exec:java

# 6. (Optional) Run the Python FastAPI defect detection service
cd defect-detection-api  # Replace with the actual directory if different
python -m venv venv
source venv/bin/activate  # On Windows use: venv\Scripts\activate
pip install -r requirements.txt
uvicorn main:app --reload

# 7. Access the application
# Spring Boot UI: http://localhost:8080
# FastAPI Docs: http://localhost:8000/docs


