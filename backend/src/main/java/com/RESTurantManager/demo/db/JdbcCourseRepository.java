package com.RESTurantManager.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.RESTurantManager.demo.db.interfaces.CourseRepository;
import com.RESTurantManager.demo.model.Course;

@Repository
public class JdbcCourseRepository implements CourseRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM courses WHERE course_id = ?", id);
    }

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
}
