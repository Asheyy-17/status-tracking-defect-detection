package com.example.status_tracking.models;

import jakarta.persistence.*;
import lombok.*;
import com.example.status_tracking.enums.Role;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Automatically set `createdAt` before saving to DB
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}