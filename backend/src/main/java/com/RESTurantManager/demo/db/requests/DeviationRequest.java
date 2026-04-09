package com.RESTurantManager.demo.db.requests;

import java.util.Date;

/**
 * Request class for creating or updating a deviation. Contains fields for deviation details such as 
 * description, date, title, and the ID of the employee who registered the deviation.
 */
public class DeviationRequest {
    private String description;
    private Date date;
    private String title;
    private int registeredBy;

    /**
     * Default constructor for DeviationRequest.
     */
    public DeviationRequest() { }

    /**
     * Parameterized constructor for DeviationRequest.
     * @param description a brief description of the deviation
     * @param date the date when the deviation was registered
     * @param title the title of the deviation
     * @param registeredBy the ID of the employee who registered the deviation
     */
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
