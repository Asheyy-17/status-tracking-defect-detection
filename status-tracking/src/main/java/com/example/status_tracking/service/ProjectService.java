package com.example.status_tracking.service;

import com.example.status_tracking.models.Project;
import com.example.status_tracking.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.status_tracking.enums.Priority;


import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    // ✅ Create a new project
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    // ✅ Get all projects
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

     // ✅ Fetch high-priority projects
     public List<Project> getHighPriorityProjects() {
        return projectRepository.findByPriority(Priority.HIGH); // Uses enum Priority.HIGH
    }

    // ✅ Get project by TRP number
    public Optional<Project> getProjectByTrpNumber(String trpNumber) {
        return projectRepository.findByTrpNumber(trpNumber);
    }

    // ✅ Update a project by TRP number
    public Project updateProject(String trpNumber, Project updatedProject) {
        Optional<Project> existingProjectOpt = projectRepository.findByTrpNumber(trpNumber);
        if (existingProjectOpt.isPresent()) {
            Project existingProject = existingProjectOpt.get();
            if (updatedProject.getProjectCode() != null) existingProject.setProjectCode(updatedProject.getProjectCode());
            if (updatedProject.getPartName() != null) existingProject.setPartName(updatedProject.getPartName());
            if (updatedProject.getRevision() != null) existingProject.setRevision(updatedProject.getRevision());
            if (updatedProject.getPriority() != null) existingProject.setPriority(updatedProject.getPriority());
            if (updatedProject.getType() != null) existingProject.setType(updatedProject.getType());

            return projectRepository.save(existingProject);
        }
        throw new RuntimeException("Project with TRP Number " + trpNumber + " not found.");
    }

    // ✅ Delete project by TRP number
    public void deleteProject(String trpNumber) {
        Optional<Project> project = projectRepository.findByTrpNumber(trpNumber);
        if (project.isPresent()) {
            projectRepository.deleteByTrpNumber(trpNumber);
        } else {
            throw new RuntimeException("Project with TRP Number " + trpNumber + " not found.");
        }
    }
}