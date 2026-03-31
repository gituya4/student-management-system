package com.example.studentapp.repository;

import com.example.studentapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * StudentRepository
 * Interface for Student database operations.
 * Spring Data JPA will automatically generate the implementation.
 */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Basic CRUD methods inherited from JpaRepository: save(), findById(), delete() etc.
}
