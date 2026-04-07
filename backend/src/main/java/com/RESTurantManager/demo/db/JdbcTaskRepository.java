package com.RESTurantManager.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.RESTurantManager.demo.db.interfaces.TaskRepository;
import com.RESTurantManager.demo.model.Task;

@Repository
public class JdbcTaskRepository implements TaskRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Task task) {
        jdbcTemplate.update(
            "INSERT INTO tasks (name, task_id, description, assigned_to, due_date, category, status, recurring, recurring_frequency) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
            task.getName(),
            task.getTaskId(),
            task.getDescription(),
            task.getAssignedTo() != null ? task.getAssignedTo().getEmployeeId() : null,
            task.getFinishBy(),
            task.getCategory(),
            task.getStatus(),
            task.getRecurring(),
            task.getRecurringFrequency().name() == null ? null : task.getRecurringFrequency().name().toUpperCase()
        );
    }

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
                        task.setRecurringFrequency(com.RESTurantManager.demo.model.RecurringFrequency.valueOf(recurringFrequency.toLowerCase()));
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

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM tasks WHERE task_id = ?", id);
    }

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
                        task.setRecurringFrequency(com.RESTurantManager.demo.model.RecurringFrequency.valueOf(recurringFrequency.toLowerCase()));
                    }
                    com.RESTurantManager.demo.model.Employee employee = new com.RESTurantManager.demo.model.Employee();
                    employee.setEmployeeId(employeeId);
                    task.setAssignedTo(employee);
                    return task;
                },
                employeeId
        ).toArray(new Task[0]);
    }
    
}
