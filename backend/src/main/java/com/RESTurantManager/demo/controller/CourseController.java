package com.RESTurantManager.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RESTurantManager.demo.db.requests.CourseRequest;
import com.RESTurantManager.demo.db.responses.CourseResponse;
import com.RESTurantManager.demo.model.Course;
import com.RESTurantManager.demo.service.CourseService;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/create")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
        Course course = new Course(courseRequest.getName(), courseRequest.getCourseId(), 
                                    courseRequest.getCompletionDate(), courseRequest.getExpirationDate(), 
                                    courseRequest.getDocumentation(), courseRequest.getDescription(),
                                    courseRequest.getEmployeeId());
        CourseResponse response = courseService.createCourse(course);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<CourseResponse> deleteCourse(@RequestBody int courseId) {
        CourseResponse response = courseService.deleteCourseById(courseId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<Course> getCourse(@RequestBody int courseId) {
        Course course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }
    
}
