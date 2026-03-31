package com.example.studentapp.service;

import com.example.studentapp.model.Registration;
import java.util.List;

/**
 * RegistrationService
 * Service Layer Interface for Student Enrollment Management.
 * Defines the contract for all registration-related business logic.
 */
public interface RegistrationService {
    
    /**
     * getAllRegistrations: retrieves every enrollment record currently in the registry
     * @return a list of all Student-Course mapping objects
     */
    List<Registration> getAllRegistrations();
    
    /**
     * getRegistrationById: finds a specific record by its primary key
     * @param id - unique registration link ID
     */
    Registration getRegistrationById(Long id);
    
    /**
     * getRegistrationsByStudentId: filters the registry to show a specific student's module enrollments
     * @param studentId - unique student identifier
     */
    List<Registration> getRegistrationsByStudentId(Long studentId);
    
    /**
     * saveRegistration: logic for committing an enrollment record to the persistent database
     * @param registration - maps student-course pair to a Java object
     */
    Registration saveRegistration(Registration registration);
    
    /**
     * deleteRegistration: Handles "Drop Course" logic by removing an enrollment record
     * @param id - the ID of the registration mapping to remove
     */
    void deleteRegistration(Long id);
    
    /**
     * countRegistrations: Statistical logic for enrollment records count
     */
    long countRegistrations();

    /**
     * countByCourseId: gets the number of students currently registered for a module
     * @param courseId - unique academic module ID
     */
    long countByCourseId(Long courseId);
}
