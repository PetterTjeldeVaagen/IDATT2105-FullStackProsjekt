package com.RESTurantManager.demo.model;

import java.io.File;
import java.util.Date;

/**
 * Model class representing a course completed by an employee. Contains fields for course details such as name, course ID, completion date, expiration date, documentation, description, and the ID of the employee who completed the course.
 */
public class Course {
    private String name;
    private int courseId;
    private Date dateCompleted;
    private Date dateExpires;
    private File documentation;
    private String description;
    private int employeeId;

    /**
     * Default constructor for Course.
     */
    public Course() { }
    
    /**
     * Parameterized constructor for Course.
     * @param name the name of the course
     * @param dateCompleted the date the course was completed
     * @param dateExpires the date the course expires
     * @param documentation the documentation file for the course
     * @param description the description of the course
     * @param employeeId the ID of the employee who completed the course
     */
    public Course(String name, Date dateCompleted, Date dateExpires, File documentation, String description, int employeeId) {
        this.name = name;
        this.dateCompleted = dateCompleted;
        this.dateExpires = dateExpires;
        this.documentation = documentation;
        this.description = description;
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Date dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public Date getDateExpires() {
        return dateExpires;
    }

    public void setDateExpires(Date dateExpires) {
        this.dateExpires = dateExpires;
    }

    public File getDocumentation() {
        return documentation;
    }

    public void setDocumentation(File documentation) {
        this.documentation = documentation;
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
}
