package com.RESTurantManager.demo.service;

import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.CourseRepository;
import com.RESTurantManager.demo.model.Course;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void createCourse(Course course) {
        courseRepository.save(course);
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id);
    }

    public void deleteCourseById(int id) {
        courseRepository.deleteById(id);
    }

}
