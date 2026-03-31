package com.example.studentapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;

/**
 * Student Entity
 * Represents the database schema for the students table.
 * Contains persistence mapping and validation constraints.
 * 
 * Note: The column for 'year' is explicitly named 'enrollment_year' 
 * to avoid conflicts with SQL reserved words in certain databases like H2.
 */
@Entity
public class Student {

    // Primary Key: Auto-generated unique identifier for each student record
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Field: Full Name - Required for student identification
    @NotBlank(message = "Student name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    // Field: Email Address - Must be unique and follow RFC standards
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Please provide a valid university email address")
    private String email;

    // Field: Academic Program - e.g., 'Computer Science', 'Information Technology'
    @NotBlank(message = "Academic program is mandatory")
    private String program;

    // Field: Enrollment Year - Must be between 1 and 5
    // Explicitly mapping to 'enrollment_year' to avoid H2 reserved word 'YEAR'
    @NotNull(message = "Enrollment year is mandatory")
    @Min(value = 1, message = "Year must be at least 1")
    @Max(value = 5, message = "Year cannot exceed 5")
    @Column(name = "enrollment_year")
    private Integer year;

    // Default Constructor: Required by JPA/Hibernate for instantiation
    public Student() {}

    /**
     * Parameterized Constructor: For manual object creation in services/tests
     */
    public Student(String name, String email, String program, Integer year) {
        this.name = name;
        this.email = email;
        this.program = program;
        this.year = year;
    }

    // --- GETTERS AND SETTERS: Providing access to private database fields ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
