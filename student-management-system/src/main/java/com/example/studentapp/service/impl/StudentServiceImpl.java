package com.example.studentapp.service.impl;

import com.example.studentapp.model.Student;
import com.example.studentapp.repository.RegistrationRepository;
import com.example.studentapp.repository.StudentRepository;
import com.example.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * StudentServiceImpl
 * Business Logic Implementation for the Student Management Module.
 * Now includes cascading deletes for linked registrations.
 */
@Service
public class StudentServiceImpl implements StudentService {

    // Dependency: studentRepository handles direct student record database access
    private final StudentRepository studentRepository;
    // Dependency: registrationRepository maintains student-course enrollment links
    private final RegistrationRepository registrationRepository;

    // constructor-based Dependency Injection: Wire required repositories into the service
    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, RegistrationRepository registrationRepository) {
        this.studentRepository = studentRepository;
        this.registrationRepository = registrationRepository;
    }

    /**
     * getAllStudents: logic for retrieving the entire student population
     * @return list of student records
     */
    @Override
    public List<Student> getAllStudents() {
        // Use JPA find() logic to pull all records from the H2 student table
        return studentRepository.findAll();
    }

    /**
     * getStudentById: Logic for finding a specific student by primary key
     * @param id - student unique identifier
     * @return student object or null if not found
     */
    @Override
    public Student getStudentById(Long id) {
        // Efficient retrieval logic via the auto-generated query method
        return studentRepository.findById(id).orElse(null);
    }

    /**
     * saveStudent: logic for committing a new student to the database
     * @param student - mapping object to save
     * @return persistent student record with generated ID
     */
    @Override
    public Student saveStudent(Student student) {
        // Business Rule: Ensure student is persistent within the system
        return studentRepository.save(student);
    }

    /**
     * deleteStudent: Logic for safely removing a student and their data
     * Uses @Transactional to ensure data integrity during multi-removal steps.
     * @param id - ID of the student being expelled/removed
     */
    @Override
    @Transactional
    public void deleteStudent(Long id) {
        // Step 1: Cleanup: Delete all registrations associated with this student
        // This prevents "orphaned" registrations that would crash the Registration page
        registrationRepository.deleteByStudentId(id);
        // Step 2: Removal: Final removal of the student profile record
        studentRepository.deleteById(id);
    }

    /**
     * countStudents: Statistical logic for current enrollment population
     */
    @Override
    public long countStudents() {
        // Core Logic: Query the count of rows in the student DB table
        return studentRepository.count();
    }
}
