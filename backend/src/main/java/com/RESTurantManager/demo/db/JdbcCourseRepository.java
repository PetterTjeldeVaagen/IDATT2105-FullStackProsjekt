package com.RESTurantManager.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
                "INSERT INTO courses (name, course_id, date_completed, date_expires, documentation) VALUES (?, ?, ?, ?, ?)",
                course.getName(),
                course.getCourseId(),
                course.getDateCompleted(),
                course.getDateExpires(),
                course.getDocumentation()
        );
    }

    @Override
    public Course findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM courses WHERE id = ?",
                new BeanPropertyRowMapper<>(Course.class),
                id
        );
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM courses WHERE id = ?", id);
    }
}
