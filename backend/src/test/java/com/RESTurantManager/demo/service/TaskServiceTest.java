package com.RESTurantManager.demo.service;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.mockito.junit.jupiter.MockitoExtension;

import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.db.interfaces.TaskRepository;
import com.RESTurantManager.demo.model.Task;
import com.RESTurantManager.demo.db.requests.TaskRequest;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setTaskId(1);
        task.setName("Test Task");
        task.setDescription("This is a test task");
        task.setStatus("PENDING");
    }

    @Test
    public void testCreateTask() {
        taskService.createTask(task);

        verify(taskRepository).save(task);
    }

    @Test
    public void testGetTaskById() {
        int taskId = 1;
        when(taskRepository.findById(taskId)).thenReturn(task);

        Task result = taskService.getTaskById(taskId);

        assertEquals(task, result);
    }

    @Test
    public void testDeleteTaskById() {
        int taskId = 1;
        taskService.deleteTaskById(taskId);

        verify(taskRepository).deleteById(taskId);
    }

    @Test
    public void testGetTasksByEmployeeId() {
        int employeeId = 1;
        Task[] tasks = new Task[]{task};
        when(taskRepository.findByEmployeeId(employeeId)).thenReturn(tasks);

        Task[] result = taskService.getTasksByEmployeeId(employeeId);

        assertEquals(1, result.length);
        assertEquals(task, result[0]);
        verify(taskRepository).findByEmployeeId(employeeId);
    }

    @Test
    public void testUpdateTask() {
        int taskId = 1;
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setName("Updated Task");
        taskRequest.setDescription("This is an updated test task");
        taskRequest.setStatus("COMPLETED");
        taskRequest.setFinishBy(new Date());
        taskRequest.setRecurring(false);
        taskRequest.setRecurringFrequency(null);
        taskRequest.setCategory("GENERAL");
        taskRequest.setAssignedTo(1);

        when(taskRepository.findById(taskId)).thenReturn(task);
        Employee assignedEmployee = new Employee();
        assignedEmployee.setEmployeeId(1);
        when(employeeService.getEmployeeById(1)).thenReturn(assignedEmployee);

        Task result = taskService.updateTask(taskId, taskRequest);

        assertEquals("Updated Task", result.getName());
        assertEquals("This is an updated test task", result.getDescription());
        assertEquals("COMPLETED", result.getStatus());
        assertNotNull(result.getFinishBy());
        assertFalse(result.getRecurring());
        assertNull(result.getRecurringFrequency());
        assertEquals("GENERAL", result.getCategory());
        assertEquals(assignedEmployee, result.getAssignedTo());

        verify(taskRepository).update(result);
    }
}


