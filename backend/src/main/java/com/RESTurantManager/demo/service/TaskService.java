package com.RESTurantManager.demo.service;

import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.TaskRepository;
import com.RESTurantManager.demo.model.Task;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public Task getTaskById(int id) {
        return taskRepository.findById(id);
    }

    public void deleteTaskById(int id) {
        taskRepository.deleteById(id);
    }

    public Task[] getTasksByEmployeeId(int employeeId) {
        return taskRepository.findByEmployeeId(employeeId);
    }
}
