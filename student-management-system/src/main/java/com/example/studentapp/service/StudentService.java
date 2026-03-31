package com.example.studentapp.service;

import com.example.studentapp.model.Student;
import java.util.List;

/**
 * StudentService
 * Defines the business contract for Student Management operations.
 * Acts as an abstraction layer between the Controller and Data Access Layer.
 */
public interface StudentService {
    
    /**
     * getAllStudents: logic for retrieving the entire population of student records
     * @return List of all students currently in the database
     */
    List<Student> getAllStudents();
    
    /**
     * getStudentById: Logic for finding a specific student by primary key
     * @param id - student unique identifier
     * @return found Student object or null if no record exists
     */
    Student getStudentById(Long id);
    
    /**
     * saveStudent: logic for committing new or modified student data to the system
     * @param student - mapping object to insert or update
     * @return the persistent student record with its generated primary key
     */
    Student saveStudent(Student student);
    
    /**
     * deleteStudent: Logic for safely removing a student and their data linkages
     * Implementation should handle cascading deletes for registrations.
     * @param id - student ID being removed
     */
    void deleteStudent(Long id);
    
    /**
     * countStudents: Returns the total count of student records for statistics
     * @return total row count of the student table
     */
    long countStudents();
}
