package com.example.studentapp.controller;

import com.example.studentapp.model.Registration;
import com.example.studentapp.service.CourseService;
import com.example.studentapp.service.RegistrationService;
import com.example.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * MainController
 * Orchestrates the central "University Console" or Dashboard.
 * Aggregates statistics and recent activity logs from all modules.
 */
@Controller
public class MainController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final RegistrationService registrationService;

    // Injecting all three core services to generate a unified system overview
    @Autowired
    public MainController(StudentService studentService, CourseService courseService, RegistrationService registrationService) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.registrationService = registrationService;
    }

    /**
     * showDashboard: Renders the primary entry point of the management system
     * @param model - UI data container
     * @return dashboard template
     */
    @GetMapping("/")
    public String showDashboard(Model model) {
        // Statistical Data: Count active records in the H2 database
        model.addAttribute("studentCount", studentService.countStudents());
        model.addAttribute("courseCount", courseService.countCourses());
        model.addAttribute("registrationCount", registrationService.countRegistrations());
        
        // Activity Feed Logic: Retrieve the most recent 5 enrollments for the dashboard sidebar
        List<Registration> allRegistrations = registrationService.getAllRegistrations();
        List<Registration> recentActivity = allRegistrations.stream()
                .sorted((a, b) -> b.getId().compareTo(a.getId())) // Sort by registration ID descending
                .limit(5)                                        // Take only the newest 5 entries
                .collect(Collectors.toList());
                
        // Pass necessary services to the view for linking student/course names to activity IDs
        model.addAttribute("recentActivity", recentActivity);
        model.addAttribute("studentService", studentService);
        model.addAttribute("courseService", courseService);
        
        // Load the dashboard.html view
        return "dashboard";
    }
}
