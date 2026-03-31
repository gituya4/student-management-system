package com.example.studentapp.service.impl;

import com.example.studentapp.model.Registration;
import com.example.studentapp.repository.RegistrationRepository;
import com.example.studentapp.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RegistrationServiceImpl
 * Core Implementation for academic enrollment business logic.
 * Manages the data layer for linking students to specific course modules.
 */
@Service
public class RegistrationServiceImpl implements RegistrationService {

    // core persistence logic handled by the RegistrationRepository interface
    private final RegistrationRepository registrationRepository;

    // constructor-based Injection: Wiring in the required repository
    @Autowired
    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    /**
     * getAllRegistrations: returns the complete academic register
     * @return enrollment registry list
     */
    @Override
    public List<Registration> getAllRegistrations() {
        // Step 1: Retrieval: Fetch all records from the H2 registration table
        return registrationRepository.findAll();
    }

    /**
     * getRegistrationById: Logic for finding a specific enrollment link
     * @param id - registration record unique identifier
     */
    @Override
    public Registration getRegistrationById(Long id) {
        // Step 1: Lookup: unique search by Primary Key
        return registrationRepository.findById(id).orElse(null);
    }

    /**
     * getRegistrationsByStudentId: Filters the registry for a specific student's profile
     * @param studentId - the ID of the student Profile
     */
    @Override
    public List<Registration> getRegistrationsByStudentId(Long studentId) {
        // Logic: Use custom find method defined in Repository interface
        return registrationRepository.findByStudentId(studentId);
    }

    /**
     * saveRegistration: logic for committing an enrollment record to the database
     * @param registration - maps student-course pair to a Java object
     * @return persistent registration record
     */
    @Override
    public Registration saveRegistration(Registration registration) {
        // Business Rule: ensures the enrollment is in the persistent database
        return registrationRepository.save(registration);
    }

    /**
     * deleteRegistration: Handles "Drop Course" logic by removing an enrollment record
     * @param id - the ID of the registration link to remove
     */
    @Override
    public void deleteRegistration(Long id) {
        // Removal Logic: Deletes by id the academic mapping from the registry
        registrationRepository.deleteById(id);
    }

    /**
     * countRegistrations: Statistical logic for enrollment records count
     */
    @Override
    public long countRegistrations() {
        // Logic: Query the count of total enrollment rows
        return registrationRepository.count();
    }

    /**
     * countByCourseId: Analytics logic for per-course enrollment population
     * @param courseId - ID of a specific catalog module
     */
    @Override
    public long countByCourseId(Long courseId) {
        // Logic: Custom count Query to find total enrollments for a specific module
        return registrationRepository.findAll().stream()
                .filter(r -> r.getCourseId().equals(courseId))
                .count();
    }
}
