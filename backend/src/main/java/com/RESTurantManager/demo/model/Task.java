package com.RESTurantManager.demo.model;

import java.util.Date;

public class Task {
    private String taskName;
    private int taskId;
    private String description;
    private Date finishBy;
    private RecurringFrequency recurringFrequency;
    private Boolean recurring;
    private Employee assignedTo;
    private Boolean status;
    private String category;
    
    public Task(String taskName, int taskId, String description, Date finishBy, RecurringFrequency recurringFrequency, Boolean recurring, Employee assignedTo, String category) {
        this.taskName = taskName;
        this.taskId = taskId;
        this.description = description;
        this.finishBy = finishBy;
        this.recurringFrequency = recurringFrequency;
        this.recurring = recurring;
        this.assignedTo = assignedTo;
        this.status = false;
        this.category = category;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
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
