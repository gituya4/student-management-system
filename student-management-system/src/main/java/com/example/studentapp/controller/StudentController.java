package com.example.studentapp.controller;

import com.example.studentapp.model.Registration;
import com.example.studentapp.model.Student;
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
 * StudentController
 * Handles all web requests related to Student Management.
 * Maps URLs to specific Thymeleaf templates and logical operations.
 */
@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;
    private final RegistrationService registrationService;
    private final CourseService courseService;

    // Constructor-based Dependency Injection: Ensuring all required services are available
    @Autowired
    public StudentController(StudentService studentService, RegistrationService registrationService, CourseService courseService) {
        this.studentService = studentService;
        this.registrationService = registrationService;
        this.courseService = courseService;
    }

    /**
     * listStudents: Fetches all students and displays the directory list
     * @param model - container that holds data for the Thymeleaf view
     * @return name of the HTML template to render
     */
    @GetMapping
    public String listStudents(Model model) {
        // Retrieve the complete list of students from the database via service layer
        List<Student> students = studentService.getAllStudents();
        // Add the retrieved list to the UI model with the key "students"
        model.addAttribute("students", students);
        // Load the students/list.html template
        return "students/list";
    }

    /**
     * showAddForm: Prepares the "Add Student" view with an empty object
     * @param model - UI data container
     * @return students/form template
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        // Create a new, empty Student object to bind to the form fields
        model.addAttribute("student", new Student());
        // Load the common form template for data entry
        return "students/form";
    }

    /**
     * saveStudent: Processes form submission for creating or updating a student
     * @param student - the data submitted from the form, mapped to a Student object
     * @param result - contains any validation errors (e.g., empty name, invalid email)
     * @return redirect to student list or back to form if errors exist
     */
    @PostMapping("/save")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult result) {
        // Validation Check: If the user inputs do not meet requirements, return to form
        if (result.hasErrors()) {
            // Rendering the form again highlights the specific validation errors to the user
            return "students/form";
        }
        // Business Logic: Save the student entity to the database
        studentService.saveStudent(student);
        // Navigation: Redirect the browser back to the student directory view
        return "redirect:/students";
    }

    /**
     * showEditForm: Loads an existing student's data into the entry form for modification
     * @param id - unique identifier of the student to be edited
     * @param model - UI data container
     * @return students/form template
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        // Data Retrieval: Fetch the existing student record by its Primary Key
        Student student = studentService.getStudentById(id);
        // Place the student data into the form binding model
        model.addAttribute("student", student);
        // Load the entry form; Thymeleaf will pre-fill fields with existing values
        return "students/form";
    }

    /**
     * deleteStudent: Handles the removal of a student and their data
     * @param id - ID of the student to remove
     * @return redirect back to the student directory
     */
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        // Logical Operation: Call service to remove the record (includes cascading delete for registrations)
        studentService.deleteStudent(id);
        // Return to the updated list view
        return "redirect:/students";
    }

    /**
     * viewProfile: Detailed student information and their course enrollments
     * @param id - ID of the student profile to view
     * @param model - UI data container
     * @return students/profile template
     */
    @GetMapping("/profile/{id}")
    public String viewProfile(@PathVariable Long id, Model model) {
        // Multi-Step Retrieval: Fetch specific student details first
        Student student = studentService.getStudentById(id);
        // Secondly, fetch all course registrations associated with this student
        List<Registration> registrations = registrationService.getRegistrationsByStudentId(id);
        
        // Model Binding: Attach all retrieved data for view rendering
        model.addAttribute("student", student);
        model.addAttribute("registrations", registrations);
        // Provide the CourseService to the view to allow lookup of course details by ID
        model.addAttribute("courseService", courseService);
        
        // Render the specialized profile dashboard view
        return "students/profile";
    }
}
