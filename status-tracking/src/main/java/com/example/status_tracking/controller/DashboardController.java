package com.example.status_tracking.controller;

import com.example.status_tracking.service.DashboardService;
import com.example.status_tracking.dto.DashboardStatsDTO;
import com.example.status_tracking.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // ✅ Get Dashboard Statistics
    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDTO> getDashboardStats() {
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }

    // ✅ Get Delayed Projects with Reasons
    @GetMapping("/delayed-projects")
    public ResponseEntity<List<Project>> getDelayedProjects() {
        return ResponseEntity.ok(dashboardService.getDelayedProjects());
    }

    // ✅ Get High-Priority Projects
    @GetMapping("/high-priority-projects")
    public ResponseEntity<List<Project>> getHighPriorityProjects() {
        return ResponseEntity.ok(dashboardService.getHighPriorityProjects());
    }

    // ✅ Get All Projects (For Total Projects Listing)
    @GetMapping("/all-projects")
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(dashboardService.getAllProjects());
    }
}
