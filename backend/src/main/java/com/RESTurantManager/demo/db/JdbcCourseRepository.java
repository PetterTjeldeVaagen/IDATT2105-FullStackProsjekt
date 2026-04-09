package com.RESTurantManager.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.RESTurantManager.demo.db.interfaces.CourseRepository;
import com.RESTurantManager.demo.model.Course;

/**
 * Repository implementation for managing Course entities using JDBC. Provides methods for saving, finding, deleting, and updating courses in the database.
 */
@Repository
public class JdbcCourseRepository implements CourseRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Saves a new course to the database.
     * @param course the course to be saved
     */
    @Override
    public void save(Course course) {
        jdbcTemplate.update(
                "INSERT INTO courses (name, description, employee_id, date_completed, date_expires, documentation, category) VALUES (?, ?, ?, ?, ?, ?, ?)",
                course.getName(),
                course.getDescription(),
                course.getEmployeeId(),
                course.getDateCompleted(),
                course.getDateExpires(),
                course.getDocumentation() != null ? course.getDocumentation().getPath() : null,
                "General"
        );
    }

    /**
     * Finds a course by its ID.
     * @param id the ID of the course
     * @return the course with the specified ID
     */
    @Override
    public Course findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM courses WHERE course_id = ?",
                (rs, rowNum) -> {
                    Course course = new Course();
                    course.setCourseId(rs.getInt("course_id"));
                    course.setName(rs.getString("name"));
                    course.setDescription(rs.getString("description"));
                    course.setEmployeeId(rs.getInt("employee_id"));
                    course.setDateCompleted(rs.getDate("date_completed"));
                    course.setDateExpires(rs.getDate("date_expires"));
                    String documentation = rs.getString("documentation");
                    if (documentation != null) {
                        course.setDocumentation(new java.io.File(documentation));
                    }
                    return course;
                },
                id
        );
    }

    /**
     * Deletes a course by its ID.
     * @param id the ID of the course to be deleted
     */
    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM courses WHERE course_id = ?", id);
    }

    /**
     * Updates an existing course in the database.
     * @param course the course to be updated
     */
    @Override
    public void update(Course course) {
        jdbcTemplate.update(
                "UPDATE courses SET name = ?, description = ?, employee_id = ?, date_completed = ?, date_expires = ?, documentation = ? WHERE course_id = ?",
                course.getName(),
                course.getDescription(),
                course.getEmployeeId(),
                course.getDateCompleted(),
                course.getDateExpires(),
                course.getDocumentation() != null ? course.getDocumentation().getPath() : null,
                course.getCourseId()
        );
    }

    /**
     * Finds courses by the ID of the associated employee.
     * @param employeeId the ID of the employee
     * @return an array of courses associated with the specified employee ID
     */
    @Override
    public Course[] findByEmployeeId(int employeeId) {
        return jdbcTemplate.query(
                "SELECT * FROM courses WHERE employee_id = ?",
                (rs, rowNum) -> {
                    Course course = new Course();
                    course.setCourseId(rs.getInt("course_id"));
                    course.setName(rs.getString("name"));
                    course.setDescription(rs.getString("description"));
                    course.setEmployeeId(rs.getInt("employee_id"));
                    course.setDateCompleted(rs.getDate("date_completed"));
                    course.setDateExpires(rs.getDate("date_expires"));
                    String documentation = rs.getString("documentation");
                    if (documentation != null) {
                        course.setDocumentation(new java.io.File(documentation));
                    }
                    return course;
                },
                employeeId
        ).toArray(new Course[0]);
    }

    /**
     * Finds courses by the ID of the associated restaurant.
     * @param resturantId the ID of the restaurant
     * @return an array of courses associated with the specified restaurant ID
     */
    @Override
    public Course[] getCoursesByResturantId(int resturantId) {
        return jdbcTemplate.query(
                "SELECT c.* FROM courses c JOIN employees e ON c.employee_id = e.employee_id WHERE e.resturant_id = ?",
                (rs, rowNum) -> {
                    Course course = new Course();
                    course.setCourseId(rs.getInt("course_id"));
                    course.setName(rs.getString("name"));
                    course.setDescription(rs.getString("description"));
                    course.setEmployeeId(rs.getInt("employee_id"));
                    course.setDateCompleted(rs.getDate("date_completed"));
                    course.setDateExpires(rs.getDate("date_expires"));
                    String documentation = rs.getString("documentation");
                    if (documentation != null) {
                        course.setDocumentation(new java.io.File(documentation));
                    }
                    return course;
                },
                resturantId
        ).toArray(new Course[0]);
    }
}
