package com.RESTurantManager.demo.service;

import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.CourseRepository;
import com.RESTurantManager.demo.model.Course;

/**
 * Service class for handling course-related operations, such as creating, retrieving, updating, and deleting courses.
 */
@Service
public class CourseService {
    private final CourseRepository courseRepository;

    /**
     * Constructor for CourseService. Initializes the course repository for managing course data.
     * @param courseRepository the repository for managing course data
     */
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    /**
     * Creates a new course by saving it to the course repository.
     * @param course the course to be created
     */
    public void createCourse(Course course) {
        courseRepository.save(course);
    }

    /**
     * Retrieves a course by its ID from the course repository.
     * @param id the ID of the course to be retrieved
     * @return the course with the specified ID
     */
    public Course getCourseById(int id) {
        return courseRepository.findById(id);
    }

    /**
     * Deletes a course by its ID from the course repository.
     * @param id the ID of the course to be deleted
     */
    public void deleteCourseById(int id) {
        courseRepository.deleteById(id);
    }

    /**
     * Updates an existing course in the course repository.
     * @param course the course with updated information to be saved
     */
    public void updateCourse(Course course) {
        courseRepository.update(course);
    }

    /**
     * Retrieves all courses associated with a specific employee ID from the course repository.
     * @param employeeId the ID of the employee whose courses are to be retrieved
     * @return an array of courses associated with the specified employee ID
     */
    public Course[] getCoursesByEmployeeId(int employeeId) {
        return courseRepository.findByEmployeeId(employeeId);
    }

    /**
     * Retrieves all courses associated with a specific restaurant ID from the course repository.
     * @param resturantId the ID of the restaurant whose courses are to be retrieved
     * @return an array of courses associated with the specified restaurant ID
     */
    public Course[] getCoursesByResturantId(int resturantId) {
        return courseRepository.getCoursesByResturantId(resturantId);
    }

}
