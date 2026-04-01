package com.RESTurantManager.demo.service;

import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.CourseRepository;
import com.RESTurantManager.demo.db.responses.CourseResponse;
import com.RESTurantManager.demo.model.Course;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public CourseResponse createCourse(Course course) {
        courseRepository.save(course);
        CourseResponse response = new CourseResponse(course.getName(), course.getDescription(), course.getEmployeeId(), course.getDateCompleted(), course.getDateExpires());
        response.setResponse("Course added successfully");
        return response;
    }

    public Course getCourseById(int id) {
        return courseRepository.findById(id);
    }

    public CourseResponse deleteCourseById(int id) {
        courseRepository.deleteById(id);
        CourseResponse response = new CourseResponse("Course deleted successfully");
        return response;
    }
}
