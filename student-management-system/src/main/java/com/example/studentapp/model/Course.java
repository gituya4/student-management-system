package com.example.studentapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

/**
 * Course Entity
 * Maps to the table representing the university course catalog.
 */
@Entity
public class Course {

    // Primary Key: Unique ID for academic module spec
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Field: Academic module Code - e.g., 'CS101'
    @NotBlank(message = "Course code is mandatory")
    private String courseCode;

    // Field: formal Module Title - descriptive name of the syllabus
    @NotBlank(message = "Course name is mandatory")
    private String courseName;

    // Field: instructor - professor assigned to the module
    @NotBlank(message = "Lecturer name is mandatory")
    private String lecturer;

    // Field: credit Points - academic weight of the module
    @NotNull(message = "Academic credits are mandatory")
    @Min(value = 1, message = "Credits must be a positive integer")
    private Integer credits;

    // Default Constructor: mandated by the Java Persistence API
    public Course() {}

    /**
     * Parameterized Constructor: For manual catalog entry creation
     */
    public Course(String courseCode, String courseName, String lecturer, Integer credits) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.lecturer = lecturer;
        this.credits = credits;
    }

    // --- GETTERS AND SETTERS: Providing access to catalog record fields ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public Integer getCredits() {
        return credits;
    }

    public void setCredits(Integer credits) {
        this.credits = credits;
    }
}
