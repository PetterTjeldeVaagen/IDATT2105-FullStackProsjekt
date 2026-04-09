package com.RESTurantManager.demo.db.responses;

import java.util.Date;

/**
 * Response class for creating or updating a deviation. Contains fields for deviation details such as 
 * description, date, title, deviation ID, the ID of the employee who registered the deviation and a response message.
 */
public class DeviationResponse {
    private String description;
    private Date date;
    private String title;
    private int deviationId;
    private int registeredBy;
    private String response;

    /**
     * Default constructor for DeviationResponse.
     */
    public DeviationResponse() { }

    /**
     * Parameterized constructor for DeviationResponse.
     * @param response a response message indicating the result of a deviation-related operation
     */
    public DeviationResponse(String response) {
        this.response = response;
    }

    /**
     * Parameterized constructor for DeviationResponse.
     * @param description a brief description of the deviation
     * @param date the date when the deviation was registered
     * @param title the title of the deviation
     * @param registeredBy the ID of the employee who registered the deviation
     * @param deviationId the ID of the deviation
     */
    public DeviationResponse(String description, Date date, String title, int registeredBy, int deviationId) {
        this.description = description;
        this.date = date;
        this.title = title;
        this.registeredBy = registeredBy;
        this.deviationId = deviationId;
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

    public int getDeviationId() {
        return deviationId;
    }

    public void setDeviationId(int deviationId) {
        this.deviationId = deviationId;
    }

    public int getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(int registeredBy) {
        this.registeredBy = registeredBy;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
