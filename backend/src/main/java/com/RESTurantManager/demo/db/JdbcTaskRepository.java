package com.RESTurantManager.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
                "INSERT INTO tasks (name, task_id, description, assigned_to, due_date, recurring, recurring_frequency) VALUES (?, ?, ?, ?, ?, ?, ?)",
                task.getTaskName(),
                task.getTaskId(),
                task.getDescription(),
                task.getAssignedTo().getEmployeeId(),
                task.getFinishBy(),
                task.getRecurring(),
                task.getRecurringFrequency(),
                task.getStatus()
        );
    }

    @Override
    public Task findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM tasks WHERE task_id = ?",
                new BeanPropertyRowMapper<>(Task.class),
                id
        );
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM tasks WHERE task_id = ?", id);
    }
    
}
