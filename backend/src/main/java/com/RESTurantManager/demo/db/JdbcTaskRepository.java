package com.RESTurantManager.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.RESTurantManager.demo.db.interfaces.TaskRepository;
import com.RESTurantManager.demo.model.Task;

/**
 * Repository class for managing tasks in the database.
 */
@Repository
public class JdbcTaskRepository implements TaskRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Saves a task to the database.
     * @param task the task to be saved
     */
    @Override
    public void save(Task task) {
        jdbcTemplate.update(
            "INSERT INTO tasks (name, description, assigned_to, due_date, category, status, recurring, recurring_frequency) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
            task.getName(),
            task.getDescription(),
            task.getAssignedTo() != null ? task.getAssignedTo().getEmployeeId() : null,
            task.getFinishBy(),
            task.getCategory(),
            task.getStatus(),
            task.getRecurring(),
            task.getRecurringFrequency() != null ? task.getRecurringFrequency().name() : null
        );
    }

    /**
     * Finds a task by its ID.
     * @param id the ID of the task
     * @return the task with the specified ID
     */
    @Override
    public Task findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM tasks WHERE task_id = ?",
                (rs, rowNum) -> {
                    Task task = new Task();
                    task.setTaskId(rs.getInt("task_id"));
                    task.setName(rs.getString("name"));
                    task.setDescription(rs.getString("description"));
                    task.setFinishBy(rs.getDate("due_date"));
                    task.setCategory(rs.getString("category"));
                    task.setStatus(rs.getString("status"));
                    task.setRecurring(rs.getBoolean("recurring"));
                    String recurringFrequency = rs.getString("recurring_frequency");
                    if (recurringFrequency != null) {
                        task.setRecurringFrequency(com.RESTurantManager.demo.model.RecurringFrequency.valueOf(recurringFrequency));
                    }
                    int assignedTo = rs.getInt("assigned_to");
                    if (!rs.wasNull()) {
                        com.RESTurantManager.demo.model.Employee employee = new com.RESTurantManager.demo.model.Employee();
                        employee.setEmployeeId(assignedTo);
                        task.setAssignedTo(employee);
                    }
                    return task;
                },
                id
        );
    }

    /**
     * Deletes a task by its ID.
     * @param id the ID of the task to be deleted
     */
    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM tasks WHERE task_id = ?", id);
    }

    /**
     * Retrieves all tasks assigned to a specific employee.
     * @param employeeId the ID of the employee
     * @return an array of tasks assigned to the specified employee
     */
    @Override
    public Task[] findByEmployeeId(int employeeId) {
        return jdbcTemplate.query(
                "SELECT * FROM tasks WHERE assigned_to = ?",
                (rs, rowNum) -> {
                    Task task = new Task();
                    task.setTaskId(rs.getInt("task_id"));
                    task.setName(rs.getString("name"));
                    task.setDescription(rs.getString("description"));
                    task.setFinishBy(rs.getDate("due_date"));
                    task.setCategory(rs.getString("category"));
                    task.setStatus(rs.getString("status"));
                    task.setRecurring(rs.getBoolean("recurring"));
                    String recurringFrequency = rs.getString("recurring_frequency");
                    if (recurringFrequency != null) {
                        task.setRecurringFrequency(com.RESTurantManager.demo.model.RecurringFrequency.valueOf(recurringFrequency));
                    }
                    com.RESTurantManager.demo.model.Employee employee = new com.RESTurantManager.demo.model.Employee();
                    employee.setEmployeeId(employeeId);
                    task.setAssignedTo(employee);
                    return task;
                },
                employeeId
        ).toArray(new Task[0]);
    }

    /**
     * Updates an existing task in the database.
     * @param task the task to be updated
     */
    @Override
    public void update(Task task) {
        jdbcTemplate.update(
            "UPDATE tasks SET name = ?, description = ?, assigned_to = ?, due_date = ?, category = ?, status = ?, recurring = ?, recurring_frequency = ? WHERE task_id = ?",
            task.getName(),
            task.getDescription(),
            task.getAssignedTo() != null ? task.getAssignedTo().getEmployeeId() : null,
            task.getFinishBy(),
            task.getCategory(),
            task.getStatus(),
            task.getRecurring(),
            task.getRecurringFrequency() != null ? task.getRecurringFrequency().name() : null,
            task.getTaskId()
        );
    }
    
}
