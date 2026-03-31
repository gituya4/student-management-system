package com.example.studentapp.controller;

import com.example.studentapp.model.Registration;
import com.example.studentapp.service.CourseService;
import com.example.studentapp.service.RegistrationService;
import com.example.studentapp.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RegistrationController
 * Manages the academic enrollment process linking students to courses.
 * Handles course registration, entry retrieval, and drop operations.
 */
@Controller
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;
    private final StudentService studentService;
    private final CourseService courseService;

    // Multi-service injection to support complex enrollment views
    @Autowired
    public RegistrationController(RegistrationService registrationService, StudentService studentService, CourseService courseService) {
        this.registrationService = registrationService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    /**
     * listRegistrations: Fetches and displays all enrollment records
     * @param model - UI data container
     * @return registration/list template
     */
    @GetMapping
    public String listRegistrations(Model model) {
        // Step 1: Fetch all registration records from the database
        List<Registration> registrations = registrationService.getAllRegistrations();
        // Step 2: Add registrations list for display
        model.addAttribute("registrations", registrations);
        // Step 3: Inject services to the view for on-the-fly student/course lookup
        model.addAttribute("studentService", studentService);
        model.addAttribute("courseService", courseService);
        // Step 4: Render the global registry view
        return "registration/list";
    }

    /**
     * showRegistrationForm: Displays the entry form for creating a new module assignment
     * @param studentId - optional parameter if registering from a specific profile
     * @param model - UI data container
     * @return registration/register template
     */
    @GetMapping("/register")
    public String showRegistrationForm(@RequestParam(required = false) Long studentId, Model model) {
        // Initialization: Create a new registration object as the form-backing bean
        Registration registration = new Registration();
        // Logic: If a student ID was provided in the URL, pre-select that student
        if (studentId != null) {
            registration.setStudentId(studentId);
        }
        
        // Data Preparation: Pass the bean and the lists of students/courses to populate dropdowns
        model.addAttribute("registration", registration);
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("courses", courseService.getAllCourses());
        // Load the registration form template
        return "registration/register";
    }

    /**
     * saveRegistration: Processes the final enrollment data
     * @param registration - maps form data to a Java object
     * @param result - holds validation results (e.g., student selection required)
     * @param model - fallback container if errors occur
     * @return redirect to registry or back to form if errors exist
     */
    @PostMapping("/save")
    public String saveRegistration(@Valid @ModelAttribute("registration") Registration registration, BindingResult result, Model model) {
        // Validation Handling: In case of missed fields or invalid data
        if (result.hasErrors()) {
            // Re-populate dropdown data so the user doesn't see a blank list on reload
            model.addAttribute("students", studentService.getAllStudents());
            model.addAttribute("courses", courseService.getAllCourses());
            // Return to the form with error messages displayed
            return "registration/register";
        }
        
        // Data Persistence: Save the valid enrollment record to the persistent database
        registrationService.saveRegistration(registration);
        // Navigation: Redirect back to the comprehensive registration list
        return "redirect:/registrations";
    }

    /**
     * deleteRegistration: Handles "Drop Course" logic by removing an enrollment record
     * @param id - the ID of the registration mapping to remove
     * @return redirect back to registry list
     */
    @GetMapping("/delete/{id}")
    public String deleteRegistration(@PathVariable Long id) {
        // Logical Operation: Call service to remove the enrollment link from the registry
        registrationService.deleteRegistration(id);
        // Return to the updated list view
        return "redirect:/registrations";
    }
}
