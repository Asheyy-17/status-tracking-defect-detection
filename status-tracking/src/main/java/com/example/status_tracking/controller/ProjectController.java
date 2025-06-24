package com.example.status_tracking.controller;

import com.example.status_tracking.models.Project;
import com.example.status_tracking.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    // Create a new project
    @PostMapping
    public ResponseEntity<?> createProject(@Valid @RequestBody Project project) {
        try {
            Project savedProject = projectService.createProject(project);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProject);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating project: " + e.getMessage());
        }
    }

    // Get all projects
    @GetMapping
    public ResponseEntity<List<Project>> getAllProjects() {
        List<Project> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    // ✅ Get total projects (Filtered API)
    @GetMapping("/total")
    public ResponseEntity<List<Project>> getTotalProjects() {
        List<Project> projects = projectService.getAllProjects();
        return projects.isEmpty() 
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() 
                : ResponseEntity.ok(projects);
    }

    // ✅ Get high-priority projects
    @GetMapping("/high-priority")
    public ResponseEntity<List<Project>> getHighPriorityProjects() {
        List<Project> highPriorityProjects = projectService.getHighPriorityProjects();
        return highPriorityProjects.isEmpty() 
                ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() 
                : ResponseEntity.ok(highPriorityProjects);
    }
    
    // Get project by TRP number
    @GetMapping("/{trpNumber}")
    public ResponseEntity<?> getProjectByTrpNumber(@PathVariable String trpNumber) {
        Optional<Project> project = projectService.getProjectByTrpNumber(trpNumber);
        return project.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update a project by TRP number
    @PutMapping("/{trpNumber}")
    public ResponseEntity<?> updateProject(@PathVariable String trpNumber, @Valid @RequestBody Project updatedProject) {
        try {
            Project updated = projectService.updateProject(trpNumber, updatedProject);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Project with TRP number " + trpNumber + " not found.");
        }
    }

    // Delete a project by TRP number
    @DeleteMapping("/{trpNumber}")
    public ResponseEntity<String> deleteProject(@PathVariable String trpNumber) {
        try {
            projectService.deleteProject(trpNumber);
            return ResponseEntity.ok("Project " + trpNumber + " deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                .body("Project with TRP number " + trpNumber + " not found.");
        }
    }

}
