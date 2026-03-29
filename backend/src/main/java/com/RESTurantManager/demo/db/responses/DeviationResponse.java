package com.RESTurantManager.demo.db.responses;

public class DeviationResponse {
    private String description;
    private String date;
    private String title;
    private int deviationId;
    private int registeredBy;

    public DeviationResponse() { }

    public DeviationResponse(String description, String date, String title, int registeredBy, int deviationId) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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

}
