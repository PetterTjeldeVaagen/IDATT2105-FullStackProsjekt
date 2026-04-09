package com.RESTurantManager.demo.db.responses;

import java.util.Date;

import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.model.RecurringFrequency;

/**
 * Response class for creating or updating a task. Contains fields for task details such as
 * name, task ID, description, finish by date, recurring frequency, recurring status, assigned employee, status, and category.
 */
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

    /**
     * Default constructor for TaskResponse.
     */
    public TaskResponse() { }

    /**
     * Parameterized constructor for TaskResponse.
     * @param name the name of the task
     * @param taskId the ID of the task
     * @param description a brief description of the task
     * @param finishBy the date by which the task should be completed
     * @param recurringFrequency the frequency at which the task recurs (if applicable)
     * @param recurring whether the task is recurring or not
     * @param assignedTo the employee to whom the task is assigned
     * @param status the current status of the task
     * @param category the category of the task
     */
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
