package com.RESTurantManager.demo.db.interfaces;

import com.RESTurantManager.demo.model.Course;

/**
 * Interface for managing courses in the database. Provides methods for saving, finding, deleting, and updating courses, as well as retrieving courses by employee ID and restaurant ID.
 */
public interface CourseRepository {
    /**
     * Saves a new course to the database.
     * @param course the course to be saved
     */
    void save(Course course);

    /**
     * Finds a course by its ID in the database.
     * @param id the ID of the course to be found
     * @return the course with the specified ID
     */
    Course findById(int id);

    /**
     * Deletes a course by its ID from the database.
     * @param id the ID of the course to be deleted
     */
    void deleteById(int id);

    /**
     * Updates an existing course in the database with new information provided in a Course object.
     * @param course the course to be updated
     */
    void update(Course course);

    /**
     * Retrieves all courses associated with a specific employee ID from the database.
     * @param employeeId the ID of the employee whose courses are to be retrieved
     * @return an array of courses associated with the specified employee ID
     */
    Course[] findByEmployeeId(int employeeId);

    /**
     * Retrieves all courses associated with a specific restaurant ID from the database.
     * @param resturantId the ID of the restaurant whose courses are to be retrieved
     * @return an array of courses associated with the specified restaurant ID
     */
    Course[] getCoursesByResturantId(int resturantId);
}
