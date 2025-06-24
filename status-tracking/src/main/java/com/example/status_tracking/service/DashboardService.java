package com.example.status_tracking.service;

import com.example.status_tracking.dto.DashboardStatsDTO;
import com.example.status_tracking.models.Project;
import com.example.status_tracking.enums.Priority;
import com.example.status_tracking.repository.ProjectRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DashboardService {

    private final ProjectRepository projectRepository;

    // ✅ Constructor Injection (Best Practice)
    public DashboardService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public DashboardStatsDTO getDashboardStats() {
        long totalProjects = projectRepository.count();
        long highPriorityProjects = projectRepository.countByPriority(Priority.HIGH);
        long delayedProjects = projectRepository.countDelayedProjects();
    
        // 🔍 Debugging logs
        System.out.println("Total Projects Count: " + totalProjects);
        System.out.println("High Priority Projects Count: " + highPriorityProjects);
        System.out.println("Delayed Projects Count: " + delayedProjects);
    
        return new DashboardStatsDTO(totalProjects, highPriorityProjects, delayedProjects);
    }

    // ✅ Fetch Delayed Projects
    public List<Project> getDelayedProjects() {
        return projectRepository.findDelayedProjects();
    }

    // ✅ Fetch High-Priority Projects
    public List<Project> getHighPriorityProjects() {
        return projectRepository.findByPriority(Priority.HIGH);
    }

    // ✅ Fetch All Projects (for total projects listing)
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }
}
