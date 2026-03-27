package com.RESTurantManager.demo.db.interfaces;

import com.RESTurantManager.demo.model.Course;

public interface CourseRepository {
    void save(Course course);

    Course findById(int id);

    void deleteById(int id);
}
