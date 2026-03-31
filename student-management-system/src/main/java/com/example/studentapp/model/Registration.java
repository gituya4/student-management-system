package com.example.studentapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

/**
 * Registration Entity
 * Linking table that maps students to course modules.
 */
@Entity
public class Registration {

    // Primary Key: Auto-generated identifier for the enrollment link
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Foreign Key Reference: Link to the Student ID
    @NotNull(message = "Student selection is required")
    private Long studentId;

    // Foreign Key Reference: Link to the Course Module ID
    @NotNull(message = "Course module selection is required")
    private Long courseId;

    // Academic Period: semester value (1, 2, or 3 as recently requested)
    @NotBlank(message = "Academic semester is mandatory")
    private String semester;

    // Default Constructor: For JPA internal initialization
    public Registration() {}

    /**
     * Parameterized Constructor: Facilitates manual registration object creation
     */
    public Registration(Long studentId, Long courseId, String semester) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.semester = semester;
    }

    // --- GETTERS AND SETTERS: Providing access to enrollment relationship details ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
}
