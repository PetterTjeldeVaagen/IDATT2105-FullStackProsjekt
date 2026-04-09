package com.RESTurantManager.demo.db.responses;

import java.util.Date;

/**
 * Response class for course-related operations. Contains fields for course details such as 
 * name, description, employee ID, completion date, expiration date, response message and course ID.
 */
public class CourseResponse {
    private String name;
    private String description;
    private int employeeId;
    private Date completionDate;
    private Date expirationDate;
    private String response;
    private int courseId;

    /**
     * Default constructor for CourseResponse.
     */
    public CourseResponse() { }

    /**
     * Parameterized constructor for CourseResponse.
     * @param response a response message indicating the result of a course-related operation
     */
    public CourseResponse(String response){
        this.response = response;
    }

    /**
     * Parameterized constructor for CourseResponse.
     * @param name the name of the course
     * @param description a brief description of the course
     * @param employeeId the ID of the employee associated with the course
     * @param completionDate the date when the course was completed
     * @param expirationDate the date when the course expires
     * @param courseId the ID of the course
     */
    public CourseResponse(String name, String description, int employeeId, Date completionDate, Date expirationDate, int courseId) {
        this.name = name;
        this.description = description;
        this.employeeId = employeeId;
        this.completionDate = completionDate;
        this.expirationDate = expirationDate;
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
