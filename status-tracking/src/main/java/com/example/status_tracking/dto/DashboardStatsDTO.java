package com.example.status_tracking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DashboardStatsDTO {
    private long totalProjects;
    private long highPriorityProjects;
    private long delayedProjects;
}
