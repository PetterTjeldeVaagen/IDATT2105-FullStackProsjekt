package com.RESTurantManager.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RESTurantManager.demo.db.requests.TaskRequest;
import com.RESTurantManager.demo.db.responses.TaskResponse;
import com.RESTurantManager.demo.model.Task;
import com.RESTurantManager.demo.service.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin(origins = "http://localhost:5173")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PutMapping("/createTask")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest taskRequest) {
        Task task = new Task(taskRequest.getTaskName(), taskRequest.getTaskId(), taskRequest.getDescription(),
                             taskRequest.getFinishBy(), taskRequest.getRecurringFrequency(), taskRequest.getRecurring(),
                             taskRequest.getAssignedTo(), taskRequest.getCategory());
        taskService.createTask(task);
        TaskResponse taskResponse = new TaskResponse(task.getTaskName(), task.getTaskId(), task.getDescription(),
                                                     task.getFinishBy(), task.getRecurringFrequency(), task.getRecurring(),
                                                     task.getAssignedTo(), task.getStatus(), task.getCategory());
        return ResponseEntity.ok(taskResponse);
    }

    @PutMapping("/deleteTask")
    public ResponseEntity<TaskResponse> deleteTask(@RequestBody TaskRequest taskRequest) {
        taskService.deleteTaskById(taskRequest.getTaskId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getTask")
    public ResponseEntity<TaskResponse> getTask(@RequestBody int taskId) {
        Task task = taskService.getTaskById(taskId);
        TaskResponse taskResponse = new TaskResponse(task.getTaskName(), task.getTaskId(), task.getDescription(),
                                                     task.getFinishBy(), task.getRecurringFrequency(), task.getRecurring(),
                                                     task.getAssignedTo(), task.getStatus(), task.getCategory());
        return ResponseEntity.ok(taskResponse);
    }
}
