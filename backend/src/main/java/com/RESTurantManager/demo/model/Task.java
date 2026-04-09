package com.RESTurantManager.demo.model;

import java.util.Date;

/**
 * Model class representing a task assigned to an employee. Contains fields for task details such as name, task ID, description, finish by date, recurring frequency, whether the task is recurring, the employee assigned to the task, status, and category.
 */
public class Task {
    private String name;
    private int taskId;
    private String description;
    private Date finishBy;
    private RecurringFrequency recurringFrequency;
    private Boolean recurring;
    private Employee assignedTo;
    private String status;
    private String category;

    /**
     * Default constructor for Task.
     */
    public Task() { }

    /**
     * Parameterized constructor for Task.
     * @param name the name of the task
     * @param description a brief description of the task
     * @param finishBy the date by which the task should be completed
     * @param recurringFrequency the frequency at which the task recurs (if applicable)
     * @param recurring whether the task is recurring or not
     * @param assignedTo the employee to whom the task is assigned
     * @param category the category of the task (e.g., "CLEANING", "MAINTENANCE", etc.)
     */
    public Task(String name, String description, Date finishBy, RecurringFrequency recurringFrequency, Boolean recurring, Employee assignedTo, String category) {
        this.name = name;
        this.description = description;
        this.finishBy = finishBy;
        this.recurringFrequency = recurringFrequency;
        this.recurring = recurring;
        this.assignedTo = assignedTo;
        this.status = "PENDING";
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getFinishBy() {
        return finishBy;
    }

    public void setFinishBy(Date finishBy) {
        this.finishBy = finishBy;
    }

    public Boolean getRecurring() {
        return recurring;
    }

    public void setRecurring(Boolean recurring) {
        this.recurring = recurring;
    }

    public Employee getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Employee assignedTo) {
        this.assignedTo = assignedTo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public RecurringFrequency getRecurringFrequency() {
        return recurringFrequency;
    }

    public void setRecurringFrequency(RecurringFrequency recurringFrequency) {
        this.recurringFrequency = recurringFrequency;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
