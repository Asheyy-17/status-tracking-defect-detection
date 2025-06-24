package com.example.status_tracking.repository;
import com.example.status_tracking.enums.Priority;

import com.example.status_tracking.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
    
    // ✅ Find a project by TRP number
    Optional<Project> findByTrpNumber(String trpNumber);
    
    // ✅ Delete a project by TRP number
    @Modifying
    @Transactional
    @Query("DELETE FROM Project p WHERE p.trpNumber = :trpNumber")
    void deleteByTrpNumber(@Param("trpNumber") String trpNumber);

    // ✅ Count total projects
    long count();

    // ✅ Count projects by priority (HIGH, MEDIUM, LOW)
    long countByPriority(Priority priority);

    // ✅ Count delayed projects
    @Query("SELECT COUNT(p) FROM Project p WHERE p.dueDate < NOW() AND p.status <> 'COMPLETED'")
    long countDelayedProjects();

    // ✅ Get all delayed projects with delay reason
    @Query("SELECT p FROM Project p WHERE p.dueDate < CURRENT_DATE")
    List<Project> findDelayedProjects();

    // ✅ Fetch projects by priority
    List<Project> findByPriority(Priority priority);
}
