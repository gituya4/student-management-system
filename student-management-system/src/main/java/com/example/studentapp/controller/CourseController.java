package com.example.studentapp.controller;

import com.example.studentapp.model.Course;
import com.example.studentapp.service.CourseService;
import com.example.studentapp.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * CourseController
 * Handles academic module management and the course catalog.
 * Provides views for module creation, listing, and statistical enrollment counts.
 */
@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final RegistrationService registrationService;

    // Constructor-based injection: linking course modules with current enrollments
    @Autowired
    public CourseController(CourseService courseService, RegistrationService registrationService) {
        this.courseService = courseService;
        this.registrationService = registrationService;
    }

    /**
     * listCourses: Loads the academic course catalog with dynamic enrollment counts
     * @param model - UI data container
     * @return courses/list template
     */
    @GetMapping
    public String listCourses(Model model) {
        // Step 1: Retrieval: Get all course definitions from the database
        List<Course> courses = courseService.getAllCourses();
        
        // Step 2: Analytics: For each course, calculate the current number of registered students
        Map<Long, Long> enrollmentCounts = courses.stream()
                .collect(Collectors.toMap(
                    Course::getId, 
                    course -> registrationService.countByCourseId(course.getId())
                ));
        
        // Step 3: Deployment: Pass catalog and analytics map to the Thymeleaf view
        model.addAttribute("courses", courses);
        model.addAttribute("enrollmentCounts", enrollmentCounts);
        
        // Load the catalog view
        return "courses/list";
    }

    /**
     * showAddForm: Displays the module creation interface
     * @param model - UI data container
     * @return courses/form template
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        // Binding: Attach a fresh Course entity for form data collection
        model.addAttribute("course", new Course());
        // Load the shared module modification form
        return "courses/form";
    }

    /**
     * saveCourse: Processes the submission of a new or existing course module
     * @param course - course data submitted from the UI
     * @param result - contains any validation feedback (e.g., missing code)
     * @return redirect or back to form
     */
    @PostMapping("/save")
    public String saveCourse(@Valid @ModelAttribute("course") Course course, BindingResult result) {
        // Integrity Check: If the form data is incomplete or invalid, discard and show errors
        if (result.hasErrors()) {
            return "courses/form";
        }
        // logical Operation: Save course specifications to the persistent catalog
        courseService.saveCourse(course);
        // Navigation: Notify browser to reload the refreshed catalog list
        return "redirect:/courses";
    }

    /**
     * showEditForm: Retrieves existing course data for updates
     * @param id - unique identifier of the module to modify
     * @param model - UI data container
     * @return courses/form template
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        // Retrieval: Fetch specific record for editing from the JPA repository
        Course course = courseService.getCourseById(id);
        // Mapping: Populate form binding object with current database values
        model.addAttribute("course", course);
        // Load the catalog entry editor
        return "courses/form";
    }

    /**
     * deleteCourse: Removes a module from the catalog
     * @param id - ID of the course to remove
     * @return redirect to catalog
     */
    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        // Operation: Remove the course record (includes cascading delete for any students enrolled in it)
        courseService.deleteCourse(id);
        // Return to the updated list view
        return "redirect:/courses";
    }
}
