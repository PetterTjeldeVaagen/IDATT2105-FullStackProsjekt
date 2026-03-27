package com.RESTurantManager.demo.model;

import java.util.Date;

public class Deviation {
    private String name;
    private int deviationId;
    private String description;
    private Employee registeredBy;
    private Date dateRegistered;

    public Deviation(String name, int deviationId, String description, Employee registeredBy, Date dateRegistered) {
        this.name = name;
        this.deviationId = deviationId;
        this.description = description;
        this.registeredBy = registeredBy;
        this.dateRegistered = dateRegistered;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDeviationId() {
        return deviationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Employee getRegisteredBy() {
        return registeredBy;
    }

    public void setRegisteredBy(Employee registeredBy) {
        this.registeredBy = registeredBy;
    }

    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
    }
}
