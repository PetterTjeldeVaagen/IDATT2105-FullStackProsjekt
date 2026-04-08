package com.RESTurantManager.demo.db.responses;

import java.util.Date;

public class CourseResponse {
    private String name;
    private String description;
    private int employeeId;
    private Date completionDate;
    private Date expirationDate;
    private String response;
    private int courseId;

    public CourseResponse() { }

    public CourseResponse(String response){
        this.response = response;
    }

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
