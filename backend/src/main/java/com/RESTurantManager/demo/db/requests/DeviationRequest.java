package com.RESTurantManager.demo.db.requests;

import java.util.Date;

public class DeviationRequest {
    private String description;
    private Date date;
    private String title;
    private int registeredBy;

    public DeviationRequest() { }

    public DeviationRequest(String description, Date date, String title, int registeredBy) {
        this.description = description;
        this.date = date;
        this.title = title;
        this.registeredBy = registeredBy;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(int registeredBy) {
        this.registeredBy = registeredBy;
    }

}
