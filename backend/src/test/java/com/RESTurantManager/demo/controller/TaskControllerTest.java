package com.RESTurantManager.demo.controller;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.RESTurantManager.demo.db.requests.TaskRequest;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.model.Task;
import com.RESTurantManager.demo.security.JwtAuthorizationFilter;
import com.RESTurantManager.demo.service.EmployeeService;
import com.RESTurantManager.demo.service.TaskService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(
    controllers = TaskController.class,
    excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthorizationFilter.class)
)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TaskService taskService;

    @MockitoBean
    private EmployeeService employeeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("createTask should return created task response")
    void createTaskTest() throws Exception {
        TaskRequest request = new TaskRequest();
        request.setName("Test Task");
        request.setDescription("This is a test task");
        request.setFinishBy(new Date());
        request.setRecurring(false);
        request.setRecurringFrequency(null);
        request.setAssignedTo(1);
        request.setCategory("GENERAL");

        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Assigned User");
        employee.setEmail("assigned@test.no");

        when(employeeService.getEmployeeById(1)).thenReturn(employee);

        mockMvc.perform(post("/task/createTask")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Task"))
                .andExpect(jsonPath("$.description").value("This is a test task"))
                .andExpect(jsonPath("$.category").value("GENERAL"))
                .andExpect(jsonPath("$.assignedTo.employeeId").value(1))
                .andExpect(jsonPath("$.assignedTo.name").value("Assigned User"));

        verify(employeeService).getEmployeeById(1);
        verify(taskService).createTask(any(Task.class));
    }

    @Test
    @DisplayName("deleteTask should return 200")
    void deleteTaskTest() throws Exception {
        mockMvc.perform(delete("/task/deleteTask/1"))
                .andExpect(status().isOk());

        verify(taskService).deleteTaskById(1);
    }

    @Test
    @DisplayName("getTask should return task response")
    void getTaskTest() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Assigned User");

        Task task = new Task();
        task.setTaskId(1);
        task.setName("Test Task");
        task.setDescription("This is a test task");
        task.setStatus("PENDING");
        task.setAssignedTo(employee);
        task.setCategory("GENERAL");

        when(taskService.getTaskById(1)).thenReturn(task);

        mockMvc.perform(get("/task/getTask/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskId").value(1))
                .andExpect(jsonPath("$.name").value("Test Task"))
                .andExpect(jsonPath("$.description").value("This is a test task"))
                .andExpect(jsonPath("$.status").value("PENDING"))
                .andExpect(jsonPath("$.category").value("GENERAL"))
                .andExpect(jsonPath("$.assignedTo.employeeId").value(1))
                .andExpect(jsonPath("$.assignedTo.name").value("Assigned User"));

        verify(taskService).getTaskById(1);
    }

    @Test
    @DisplayName("getTasksByEmployee should return list of task responses for employee")
    void getTasksByEmployeeTest() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Assigned User");

        Task task1 = new Task();
        task1.setTaskId(1);
        task1.setName("Task One");
        task1.setDescription("First task");
        task1.setStatus("PENDING");
        task1.setAssignedTo(employee);
        task1.setCategory("GENERAL");

        Task task2 = new Task();
        task2.setTaskId(2);
        task2.setName("Task Two");
        task2.setDescription("Second task");
        task2.setStatus("COMPLETED");
        task2.setAssignedTo(employee);
        task2.setCategory("CLEANING");

        Task[] tasks = new Task[] { task1, task2 };

        when(taskService.getTasksByEmployeeId(1)).thenReturn(tasks);

        mockMvc.perform(get("/task/getTaskByEmployee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].taskId").value(1))
                .andExpect(jsonPath("$[0].name").value("Task One"))
                .andExpect(jsonPath("$[0].status").value("PENDING"))
                .andExpect(jsonPath("$[1].taskId").value(2))
                .andExpect(jsonPath("$[1].name").value("Task Two"))
                .andExpect(jsonPath("$[1].status").value("COMPLETED"));

        verify(taskService).getTasksByEmployeeId(1);
    }

    @Test
    @DisplayName("getTasksByResturant should return combined list of task responses for restaurant")
    void getTasksByResturantTest() throws Exception {
        Employee employee1 = new Employee();
        employee1.setEmployeeId(1);
        employee1.setName("Ola");

        Employee employee2 = new Employee();
        employee2.setEmployeeId(2);
        employee2.setName("Kari");

        Employee[] employees = new Employee[] { employee1, employee2 };

        Task task1 = new Task();
        task1.setTaskId(1);
        task1.setName("Kitchen Check");
        task1.setDescription("Check kitchen");
        task1.setStatus("PENDING");
        task1.setAssignedTo(employee1);
        task1.setCategory("GENERAL");

        Task task2 = new Task();
        task2.setTaskId(2);
        task2.setName("Clean Floor");
        task2.setDescription("Clean floor");
        task2.setStatus("COMPLETED");
        task2.setAssignedTo(employee2);
        task2.setCategory("CLEANING");

        when(employeeService.getEmployeesByResturantId(1)).thenReturn(employees);
        when(taskService.getTasksByEmployeeId(1)).thenReturn(new Task[] { task1 });
        when(taskService.getTasksByEmployeeId(2)).thenReturn(new Task[] { task2 });

        mockMvc.perform(get("/task/getTasksByResturant/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].taskId").value(1))
                .andExpect(jsonPath("$[0].name").value("Kitchen Check"))
                .andExpect(jsonPath("$[1].taskId").value(2))
                .andExpect(jsonPath("$[1].name").value("Clean Floor"));

        verify(employeeService).getEmployeesByResturantId(1);
        verify(taskService).getTasksByEmployeeId(1);
        verify(taskService).getTasksByEmployeeId(2);
    }

    @Test
    @DisplayName("updateTask should return updated task response")
    void updateTaskTest() throws Exception {
        TaskRequest request = new TaskRequest();
        request.setName("Updated Task");
        request.setDescription("Updated description");
        request.setStatus("COMPLETED");
        request.setFinishBy(new Date());
        request.setRecurring(false);
        request.setRecurringFrequency(null);
        request.setCategory("GENERAL");
        request.setAssignedTo(1);

        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Assigned User");

        Task updatedTask = new Task();
        updatedTask.setTaskId(1);
        updatedTask.setName("Updated Task");
        updatedTask.setDescription("Updated description");
        updatedTask.setStatus("COMPLETED");
        updatedTask.setFinishBy(request.getFinishBy());
        updatedTask.setRecurring(false);
        updatedTask.setRecurringFrequency(null);
        updatedTask.setCategory("GENERAL");
        updatedTask.setAssignedTo(employee);

        when(taskService.updateTask(any(Integer.class), any(TaskRequest.class))).thenReturn(updatedTask);

        mockMvc.perform(put("/task/updateTask/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.taskId").value(1))
                .andExpect(jsonPath("$.name").value("Updated Task"))
                .andExpect(jsonPath("$.description").value("Updated description"))
                .andExpect(jsonPath("$.status").value("COMPLETED"))
                .andExpect(jsonPath("$.category").value("GENERAL"))
                .andExpect(jsonPath("$.assignedTo.employeeId").value(1))
                .andExpect(jsonPath("$.assignedTo.name").value("Assigned User"));

        verify(taskService).updateTask(any(Integer.class), any(TaskRequest.class));
    }
}