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

/**
 * Controller for handling course related endpoints such as creating, updating, deleting and retrieving courses.
 */
@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://localhost:5173")
public class CourseController {
    private final CourseService courseService;

    /**
     * Constructor for CourseController.
     * @param courseService the service for managing course operations
     */
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    /**
     * Endpoint for creating a new course.
     * @param courseRequest the request containing course details
     * @return ResponseEntity containing the created course response
     */
    @PostMapping("/createCourse")
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest courseRequest) {
        Course course = new Course(courseRequest.getName(),  
                                    courseRequest.getCompletionDate(), courseRequest.getExpirationDate(), 
                                    courseRequest.getDocumentation(), courseRequest.getDescription(),
                                    courseRequest.getEmployeeId());
        courseService.createCourse(course);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint for deleting a course by its ID.
     * @param courseId the ID of the course to be deleted
     * @return ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/deleteCourse/{courseId}")
    public ResponseEntity<CourseResponse> deleteCourse(@PathVariable int courseId) {
        courseService.deleteCourseById(courseId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint for retrieving a course by its ID.
     * @param courseId the ID of the course to be retrieved
     * @return ResponseEntity containing the retrieved course response
     */
    @GetMapping("/getCourse/{courseId}")
    public ResponseEntity<CourseResponse> getCourse(@PathVariable int courseId) {
        Course course = courseService.getCourseById(courseId);
        CourseResponse courseResponse = new CourseResponse(course.getName(), course.getDescription(), 
                                                           course.getEmployeeId(), course.getDateCompleted(), course.getDateExpires(), course.getCourseId());
        return ResponseEntity.ok(courseResponse);
    }

    /**
     * Endpoint for retrieving courses by employee ID.
     * @param employeeId the ID of the employee whose courses are to be retrieved
     * @return ResponseEntity containing the retrieved courses response
     */
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

    /**
     * Endpoint for retrieving courses by restaurant ID.
     * @param resturantId the ID of the restaurant whose courses are to be retrieved
     * @return ResponseEntity containing the retrieved courses response
     */
    @GetMapping("/getCoursesByResturant/{resturantId}")
    public ResponseEntity<CourseResponse[]> getCoursesByResturantId(@PathVariable int resturantId) {
        Course[] courses = courseService.getCoursesByResturantId(resturantId);
        CourseResponse[] courseResponses = new CourseResponse[courses.length];
        for (int i = 0; i < courses.length; i++) {
            courseResponses[i] = new CourseResponse(courses[i].getName(), courses[i].getDescription(), 
                                                   courses[i].getEmployeeId(), courses[i].getDateCompleted(), courses[i].getDateExpires(), courses[i].getCourseId());
        }
        return ResponseEntity.ok(courseResponses);
    }

    /**
     * Endpoint for updating a course by its ID.
     * @param courseId the ID of the course to be updated
     * @param courseRequest the request containing updated course details
     * @return ResponseEntity indicating the result of the update operation
     */
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
