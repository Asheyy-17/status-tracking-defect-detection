package com.example.status_tracking.controller;

import com.example.status_tracking.service.DefectDetectionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/defect")
public class DefectController {

    private final DefectDetectionService defectDetectionService;

    public DefectController(DefectDetectionService defectDetectionService) {
        this.defectDetectionService = defectDetectionService;
    }

    @PostMapping("/predict")
    public ResponseEntity<?> predictDefect(@RequestParam("files") MultipartFile file) {
        try {
            String result = defectDetectionService.predictDefect(file);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}