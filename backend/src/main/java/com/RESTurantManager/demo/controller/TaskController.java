package com.RESTurantManager.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RESTurantManager.demo.db.requests.TaskRequest;
import com.RESTurantManager.demo.db.responses.TaskResponse;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.model.Task;
import com.RESTurantManager.demo.service.EmployeeService;
import com.RESTurantManager.demo.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller for handling task related endpoints such as creating, updating, deleting and retrieving tasks.
 */
@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "TaskController", description = "Controller for handling task related endpoints such as creating, updating, deleting and retrieving tasks.")
public class TaskController {
    private final TaskService taskService;
    private final EmployeeService employeeService;

    /**
     * Constructor for TaskController.
     * @param taskService the service for managing task operations
     * @param employeeService the service for managing employee operations
     */
    public TaskController(TaskService taskService, EmployeeService employeeService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

    /**
     * Endpoint for creating a new task.
     * @param taskRequest the request containing task details
     * @return ResponseEntity containing the created task response
     */
    @Operation(summary = "Create a new task")
    @PostMapping("/createTask")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        Task task = new Task(taskRequest.getName(), taskRequest.getDescription(),
                             taskRequest.getFinishBy(), taskRequest.getRecurringFrequency(), taskRequest.getRecurring(),
                             employeeService.getEmployeeById(taskRequest.getAssignedTo()), taskRequest.getCategory());
        taskService.createTask(task);

        TaskResponse taskResponse = new TaskResponse(task.getName(), task.getTaskId(), task.getDescription(),
                                                     task.getFinishBy(), task.getRecurringFrequency(), task.getRecurring(),
                                                     task.getAssignedTo(), task.getStatus(), task.getCategory());
        return ResponseEntity.ok(taskResponse);
    }

    /**
     * Endpoint for deleting a task by its ID.
     * @param taskId the ID of the task to be deleted
     * @return ResponseEntity indicating the result of the delete operation
     */
    @Operation(summary = "Delete a task by its ID")
    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable int taskId) {
        taskService.deleteTaskById(taskId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint for retrieving a task by its ID.
     * @param taskId the ID of the task to be retrieved
     * @return ResponseEntity containing the retrieved task response
     */
    @Operation(summary = "Get a task by its ID")
    @GetMapping("/getTask/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable int taskId) {
        Task task = taskService.getTaskById(taskId);
        TaskResponse taskResponse = new TaskResponse(task.getName(), task.getTaskId(), task.getDescription(),
                                                     task.getFinishBy(), task.getRecurringFrequency(), task.getRecurring(),
                                                     task.getAssignedTo(), task.getStatus(), task.getCategory());
        return ResponseEntity.ok(taskResponse);
    }

    /**
     * Endpoint for retrieving tasks by employee ID.
     * @param employeeId the ID of the employee whose tasks are to be retrieved
     * @return ResponseEntity containing the retrieved tasks response
     */
    @Operation(summary = "Get tasks by employee ID")
    @GetMapping("/getTaskByEmployee/{employeeId}")
    public ResponseEntity<TaskResponse[]> getTasksByEmployee(@PathVariable int employeeId) {
        Task[] tasks = taskService.getTasksByEmployeeId(employeeId);
        TaskResponse[] taskResponses = new TaskResponse[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            Task task = tasks[i];
            taskResponses[i] = new TaskResponse(task.getName(), task.getTaskId(), task.getDescription(),
                                               task.getFinishBy(), task.getRecurringFrequency(), task.getRecurring(),
                                               task.getAssignedTo(), task.getStatus(), task.getCategory());
        }
        return ResponseEntity.ok(taskResponses);
    }

    /**
     * Endpoint for retrieving tasks by restaurant ID.
     * @param resturantId the ID of the restaurant whose tasks are to be retrieved
     * @return ResponseEntity containing the retrieved tasks response
     */
    @Operation(summary = "Get tasks by restaurant ID")
    @GetMapping("/getTasksByResturant/{resturantId}")
    public ResponseEntity<TaskResponse[]> getTasksByResturant(@PathVariable int resturantId) {
        Employee[] employees = employeeService.getEmployeesByResturantId(resturantId);
        ArrayList<Task> tasks = new ArrayList<>();
        for (Employee employee : employees) {
            Task[] employeeTasks = taskService.getTasksByEmployeeId(employee.getEmployeeId());
            tasks.addAll(Arrays.asList(employeeTasks));
        }
        TaskResponse[] taskResponses = new TaskResponse[tasks.size()];
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            taskResponses[i] = new TaskResponse(task.getName(), task.getTaskId(), task.getDescription(),
                                               task.getFinishBy(), task.getRecurringFrequency(), task.getRecurring(),
                                               task.getAssignedTo(), task.getStatus(), task.getCategory());
        }
        return ResponseEntity.ok(taskResponses);
    }

    /**
     * Endpoint for updating a task by its ID.
     * @param taskId the ID of the task to be updated
     * @param taskRequest the request containing updated task details
     * @return ResponseEntity containing the updated task response
     */
    @Operation(summary = "Update a task by its ID")
    @PutMapping("/updateTask/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable int taskId, @RequestBody TaskRequest taskRequest) {
        Task task = taskService.updateTask(taskId, taskRequest);
        TaskResponse taskResponse = new TaskResponse(task.getName(), task.getTaskId(), task.getDescription(),
                                                     task.getFinishBy(), task.getRecurringFrequency(), task.getRecurring(),
                                                     task.getAssignedTo(), task.getStatus(), task.getCategory());
        return ResponseEntity.ok(taskResponse);
    }
}
