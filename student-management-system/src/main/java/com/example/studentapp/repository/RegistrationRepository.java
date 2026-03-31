package com.example.studentapp.repository;

import com.example.studentapp.model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * RegistrationRepository
 * Data Access Layer for Course Enrollments.
 */
@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    
    // Custom method to fetch all registrations for a specific student ID
    List<Registration> findByStudentId(Long studentId);
    
    /**
     * deleteByStudentId: removes student records from the enrollment links table
     * Used for cascading cleanup.
     * @param studentId - the ID being cleared
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Registration r WHERE r.studentId = ?1")
    void deleteByStudentId(Long studentId);
    
    /**
     * deleteByCourseId: removes course modules from the enrollment links table
     * Used for catalog module retirement.
     * @param courseId - module unique identifier
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Registration r WHERE r.courseId = ?1")
    void deleteByCourseId(Long courseId);
}
