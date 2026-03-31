package com.example.studentapp.service;

import com.example.studentapp.model.Course;
import java.util.List;

/**
 * CourseService
 * Service Layer Interface for Academic Catalog Management.
 * Defines the contract for all course-related business logic.
 */
public interface CourseService {
    
    /**
     * getAllCourses: returns the complete academic catalog
     * @return catalog list from the course table
     */
    List<Course> getAllCourses();
    
    /**
     * getCourseById: Retrieves a specific course definition
     * @param id - academic module unique ID
     * @return found course or null
     */
    Course getCourseById(Long id);
    
    /**
     * saveCourse: Logic for adding a new module or updating an existing one
     * @param course - module details to save
     */
    Course saveCourse(Course course);
    
    /**
     * deleteCourse: logic for removing a retired course and its data
     * @param id - unique module ID to remove
     */
    void deleteCourse(Long id);
    
    /**
     * countCourses: returns the total number of catalog modules
     */
    long countCourses();
}
