package com.example.status_tracking.models;

import jakarta.persistence.*;
import lombok.*;
import com.example.status_tracking.enums.Priority;
import com.example.status_tracking.enums.ProjectType;
import org.hibernate.annotations.CreationTimestamp; // ✅ Import for automatic timestamp
import java.sql.Timestamp; // ✅ Use correct type for createdAt
import java.time.LocalDate;
import com.example.status_tracking.enums.ProjectStatus;

@Entity
@Table(name = "projects")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Project {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String trpNumber;

    @Column(nullable = false)
    private String projectCode;

    @Column(nullable = false)
    private LocalDate dueDate;

    private String partName;
    private String revision;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProjectStatus status;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private ProjectType type;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp // ✅ Auto-generate timestamp on insert
    private Timestamp createdAt;
}
