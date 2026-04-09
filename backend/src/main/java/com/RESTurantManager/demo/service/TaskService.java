package com.RESTurantManager.demo.service;

import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.TaskRepository;
import com.RESTurantManager.demo.db.requests.TaskRequest;
import com.RESTurantManager.demo.model.Task;

/**
 * Service class for handling task-related operations, such as creating, retrieving, updating, and deleting tasks.
 */
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final EmployeeService employeeService;

    /**
     * Constructor for TaskService. Initializes the task repository and employee service for managing task data and associated employee operations.
     * @param taskRepository the repository for managing task data
     * @param employeeService the service for handling employee-related operations
     */
    public TaskService(TaskRepository taskRepository, EmployeeService employeeService) {
        this.taskRepository = taskRepository;
        this.employeeService = employeeService;
    }

    /**
     * Creates a new task by saving it to the task repository.
     * @param task the task to be created
     */
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    /**
     * Retrieves a task by its ID from the task repository.
     * @param id the ID of the task to be retrieved
     * @return the task with the specified ID
     */
    public Task getTaskById(int id) {
        return taskRepository.findById(id);
    }

    /**
     * Deletes a task by its ID from the task repository.
     * @param id the ID of the task to be deleted
     */
    public void deleteTaskById(int id) {
        taskRepository.deleteById(id);
    }

    /**
     * Retrieves all tasks associated with a specific employee ID from the task repository.
     * @param employeeId the ID of the employee whose tasks are to be retrieved
     * @return an array of tasks associated with the specified employee ID
     */
    public Task[] getTasksByEmployeeId(int employeeId) {
        return taskRepository.findByEmployeeId(employeeId);
    }

    /**
     * Updates an existing task in the task repository with new information provided in a TaskRequest object. The task's assigned employee is updated based on the employee ID specified in the TaskRequest.
     * @param taskId the ID of the task to be updated
     * @param taskRequest a TaskRequest object containing the updated information for the task
     * @return the updated task after being saved to the repository
     * @throws IllegalArgumentException if the task with the specified ID is not found in the repository
     */
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
