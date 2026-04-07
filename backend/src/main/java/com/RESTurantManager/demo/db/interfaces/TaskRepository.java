package com.RESTurantManager.demo.db.interfaces;

import com.RESTurantManager.demo.model.Task;

public interface TaskRepository {
    void save(Task task);
    
    Task findById(int id);

    void deleteById(int id);

    Task[] findByEmployeeId(int employeeId);

    void update(Task task);
}
