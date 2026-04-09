package com.RESTurantManager.demo.db.requests;

import java.io.File;
import java.util.Date;

/**
 * Request class for creating or updating a course. Contains fields for course details such as 
 * name, description, employee ID, completion date, expiration date and documentation.
 */
public class CourseRequest {
    private String name;
    private String description;
    private int employeeId;
    private Date completionDate;
    private Date expirationDate;
    private File documentation;

    /**
     * Default constructor for CourseRequest.
     */
    public CourseRequest() { }

    /**
     * Parameterized constructor for CourseRequest.
     * @param name the name of the course
     * @param description a brief description of the course
     * @param employeeId the ID of the employee associated with the course
     * @param completionDate the date when the course was completed
     * @param expirationDate the date when the course expires
     * @param documentation any relevant documentation for the course
     */
    public CourseRequest(String name, String description, int employeeId, Date completionDate, Date expirationDate, File documentation) {
        this.name = name;
        this.description = description;
        this.employeeId = employeeId;
        this.completionDate = completionDate;
        this.expirationDate = expirationDate;
        this.documentation = documentation;
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

    public File getDocumentation() {
        return documentation;
    }

    public void setDocumentation(File documentation) {
        this.documentation = documentation;
    }
}
