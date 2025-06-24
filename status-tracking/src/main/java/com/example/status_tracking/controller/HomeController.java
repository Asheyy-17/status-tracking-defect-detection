package com.example.status_tracking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String showDashboard() {
        return "dashboard_page";
    }

    @GetMapping("/projects")
    public String showProjects() {
        return "project_management";
    }

    @GetMapping("/production")
    public String showProduction() {
        return "production_tracking";
    }

    @GetMapping("/users")
    public String showUsers() {
        return "user_management_page";
    }

    @GetMapping("/reports")
    public String showReports() {
        return "report_generation";
    }

    @GetMapping("/settings")
    public String showSettings() {
        return "settings";
    }
}
