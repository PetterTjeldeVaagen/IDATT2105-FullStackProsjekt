package com.RESTurantManager.demo.model;

import java.util.Date;

/**
 * Model class representing a deviation reported by an employee. Contains fields for deviation details such as name, deviation ID, description, the employee who registered the deviation, and the date it was registered.
 */
public class Deviation {
    private String name;
    private int deviationId;
    private String description;
    private Employee registeredBy;
    private Date dateRegistered;

    /**
     * Default constructor for Deviation.
     */
    public Deviation() {
        this.deviationId = 0; 
    }
    
    /**
     * Parameterized constructor for Deviation.
     * @param name the name of the deviation
     * @param description a brief description of the deviation
     * @param registeredBy the employee who registered the deviation
     * @param dateRegistered the date when the deviation was registered
     */
    public Deviation(String name, String description, Employee registeredBy, Date dateRegistered) {
        this.name = name;
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

    public void setDeviationId(int deviationId) {
        this.deviationId = deviationId;
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
