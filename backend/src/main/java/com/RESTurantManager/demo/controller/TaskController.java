package com.RESTurantManager.demo.controller;

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
import com.RESTurantManager.demo.model.Task;
import com.RESTurantManager.demo.service.EmployeeService;
import com.RESTurantManager.demo.service.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {
    private final TaskService taskService;
    private final EmployeeService employeeService;

    public TaskController(TaskService taskService, EmployeeService employeeService) {
        this.taskService = taskService;
        this.employeeService = employeeService;
    }

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

    @DeleteMapping("/deleteTask/{taskId}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable int taskId) {
        taskService.deleteTaskById(taskId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getTask/{taskId}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable int taskId) {
        Task task = taskService.getTaskById(taskId);
        TaskResponse taskResponse = new TaskResponse(task.getName(), task.getTaskId(), task.getDescription(),
                                                     task.getFinishBy(), task.getRecurringFrequency(), task.getRecurring(),
                                                     task.getAssignedTo(), task.getStatus(), task.getCategory());
        return ResponseEntity.ok(taskResponse);
    }

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

    @PutMapping("/updateTask/{taskId}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable int taskId, @RequestBody TaskRequest taskRequest) {
        Task task = taskService.updateTask(taskId, taskRequest);
        TaskResponse taskResponse = new TaskResponse(task.getName(), task.getTaskId(), task.getDescription(),
                                                     task.getFinishBy(), task.getRecurringFrequency(), task.getRecurring(),
                                                     task.getAssignedTo(), task.getStatus(), task.getCategory());
        return ResponseEntity.ok(taskResponse);
    }
}
