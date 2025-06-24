package com.example.status_tracking.repository;

import com.example.status_tracking.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.status_tracking.enums.Priority;

import java.util.List;

@Repository
public interface DashboardRepository extends JpaRepository<Project, Integer> {

    // ✅ Count total high-priority projects
    long countByPriority(Priority priority);

    // ✅ Count delayed projects
    @Query("SELECT COUNT(p) FROM Project p WHERE p.status = 'DELAYED'")
    long countDelayedProjects();

    // ✅ Fetch all delayed projects
    @Query("SELECT p FROM Project p WHERE p.status = 'DELAYED'")
    List<Project> findDelayedProjects();
}
