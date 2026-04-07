package com.RESTurantManager.demo.service;

import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.TaskRepository;
import com.RESTurantManager.demo.db.requests.TaskRequest;
import com.RESTurantManager.demo.model.Task;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeService employeeService;

    public TaskService(TaskRepository taskRepository, EmployeeService employeeService) {
        this.taskRepository = taskRepository;
        this.employeeService = employeeService;
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

    public Task updateTask(int taskId, TaskRequest taskRequest) {
        Task task = taskRepository.findById(taskId);
        if (task == null) {
            throw new IllegalArgumentException("Task not found");
        }
        task.setStatus(taskRequest.getStatus());
        task.setName(taskRequest.getName());
        task.setDescription(taskRequest.getDescription());
        task.setFinishBy(taskRequest.getFinishBy());
        task.setRecurring(taskRequest.getRecurring());
        task.setRecurringFrequency(taskRequest.getRecurringFrequency());
        task.setCategory(taskRequest.getCategory());
        task.setAssignedTo(employeeService.getEmployeeById(taskRequest.getAssignedTo()));
        taskRepository.update(task);
        return task;
    }
}
