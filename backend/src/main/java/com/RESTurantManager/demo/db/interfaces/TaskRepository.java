package com.RESTurantManager.demo.db.interfaces;

import com.RESTurantManager.demo.model.Task;

/**
 * Interface for managing tasks in the database. Provides methods for saving, finding, deleting, and updating tasks, as well as retrieving tasks by employee ID.
 */
public interface TaskRepository {
    /**
     * Saves a new task to the database.
     * @param task the task to be saved
     */
    void save(Task task);
    
    /**
     * Finds a task by its ID in the database.
     * @param id the ID of the task to be found
     * @return the task with the specified ID
     */
    Task findById(int id);

    /**
     * Deletes a task by its ID from the database.
     * @param id the ID of the task to be deleted
     */
    void deleteById(int id);

    /**
     * Retrieves all tasks associated with a specific employee ID from the database.
     * @param employeeId the ID of the employee whose tasks are to be retrieved
     * @return an array of tasks associated with the specified employee ID
     */
    Task[] findByEmployeeId(int employeeId);

    /**
     * Updates an existing task in the database with new information provided in a Task object.
     * @param task the task to be updated
     */
    void update(Task task);
}
