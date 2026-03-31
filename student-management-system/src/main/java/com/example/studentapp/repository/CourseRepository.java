package com.example.studentapp.repository;

import com.example.studentapp.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CourseRepository
 * Service interface for Academic Catalog persistence.
 */
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    // Standard CRUD provided by Spring Data JPA
}
