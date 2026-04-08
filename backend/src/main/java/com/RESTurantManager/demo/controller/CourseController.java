package com.RESTurantManager.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/createCourse")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
        Course course = new Course(courseRequest.getName(),  
                                    courseRequest.getCompletionDate(), courseRequest.getExpirationDate(), 
                                    courseRequest.getDocumentation(), courseRequest.getDescription(),
                                    courseRequest.getEmployeeId());
        courseService.createCourse(course);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteCourse/{courseId}")
    public ResponseEntity<CourseResponse> deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourseById(courseId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getCourse/{courseId}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable int courseId) {
        Course course = courseService.getCourseById(courseId);
        CourseResponse courseResponse = new CourseResponse(course.getName(), course.getDescription(), 
                                                           course.getEmployeeId(), course.getDateCompleted(), course.getDateExpires(), course.getCourseId());
        return ResponseEntity.ok(courseResponse);
    }

    @GetMapping("/getCoursesByEmployee/{employeeId}")
    public ResponseEntity<CourseResponse[]> getCoursesByEmployeeId(@PathVariable int employeeId) {
        Course[] courses = courseService.getCoursesByEmployeeId(employeeId);
        CourseResponse[] courseResponses = new CourseResponse[courses.length];
        for (int i = 0; i < courses.length; i++) {
            courseResponses[i] = new CourseResponse(courses[i].getName(), courses[i].getDescription(), 
                                                   courses[i].getEmployeeId(), courses[i].getDateCompleted(), courses[i].getDateExpires(), courses[i].getCourseId());
        }
        return ResponseEntity.ok(courseResponses);
    }

    @PostMapping("/updateCourse/{courseId}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable int courseId, @RequestBody CourseRequest courseRequest) {
        Course course = new Course(courseRequest.getName(),
                                    courseRequest.getCompletionDate(), courseRequest.getExpirationDate(), 
                                    courseRequest.getDocumentation(), courseRequest.getDescription(),
                                    courseRequest.getEmployeeId());

        course.setCourseId(courseId);
        courseService.updateCourse(course);
        return ResponseEntity.ok().build();
    }
    
}
