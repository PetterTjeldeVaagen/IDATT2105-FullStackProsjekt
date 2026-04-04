package com.RESTurantManager.demo.db.responses;

import java.util.Date;

import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.model.RecurringFrequency;

public class TaskResponse {
    private String name;
    private int taskId;
    private String description;
    private Date finishBy;
    private RecurringFrequency recurringFrequency;
    private Boolean recurring;
    private Employee assignedTo;
    private String status;
    private String category;

    public TaskResponse() { }

    public TaskResponse(String name, int taskId, String description, Date finishBy, RecurringFrequency recurringFrequency, Boolean recurring, Employee assignedTo, String status, String category) {
        this.name = name;
        this.taskId = taskId;
        this.description = description;
        this.finishBy = finishBy;
        this.recurringFrequency = recurringFrequency;
        this.recurring = recurring;
        this.assignedTo = assignedTo;
        this.status = status;
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

    public RecurringFrequency getRecurringFrequency() {
        return recurringFrequency;
    }

    public void setRecurringFrequency(RecurringFrequency recurringFrequency) {
        this.recurringFrequency = recurringFrequency;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
