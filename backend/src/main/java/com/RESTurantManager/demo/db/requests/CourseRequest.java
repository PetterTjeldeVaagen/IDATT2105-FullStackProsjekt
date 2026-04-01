package com.RESTurantManager.demo.db.requests;

import java.io.File;
import java.util.Date;

public class CourseRequest {
    private String name;
    private String description;
    private int employeeId;
    private Date completionDate;
    private Date expirationDate;
    private File documentation;
    private int courseId;

    public CourseRequest() { }

    public CourseRequest(String name, String description, int employeeId, Date completionDate, Date expirationDate, File documentation, int courseId) {
        this.name = name;
        this.description = description;
        this.employeeId = employeeId;
        this.completionDate = completionDate;
        this.expirationDate = expirationDate;
        this.documentation = documentation;
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

    public File getDocumentation() {
        return documentation;
    }

    public void setDocumentation(File documentation) {
        this.documentation = documentation;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
