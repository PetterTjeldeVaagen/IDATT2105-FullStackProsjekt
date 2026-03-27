package com.RESTurantManager.demo.model;

import java.io.File;
import java.util.Date;

public class Course {
    private String name;
    private int courseId;
    private Date dateCompleted;
    private Date dateExpires;
    private File documentation;

    public Course(String name, int courseId, Date dateCompleted, Date dateExpires, File documentation) {
        this.name = name;
        this.courseId = courseId;
        this.dateCompleted = dateCompleted;
        this.dateExpires = dateExpires;
        this.documentation = documentation;
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
}
