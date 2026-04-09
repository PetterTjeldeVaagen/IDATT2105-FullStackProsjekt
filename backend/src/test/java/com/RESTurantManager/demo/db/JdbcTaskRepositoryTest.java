package com.RESTurantManager.demo.db;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.RESTurantManager.demo.db.interfaces.EmployeeRepository;
import com.RESTurantManager.demo.db.interfaces.TaskRepository;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.model.Task;

@SpringBootTest
@Transactional
class JdbcTaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("findByEmployeeId should return seeded tasks")
    void findByEmployeeIdTest() {
        Employee employee = employeeRepository.findByEmail("ola@test.no");

        Task[] tasks = taskRepository.findByEmployeeId(employee.getEmployeeId());

        assertNotNull(tasks);
        assertTrue(tasks.length >= 1);
    }

    @Test
    @DisplayName("save should persist task so it can be found again")
    void saveTaskTest() {
        Employee employee = employeeRepository.findByEmail("ola@test.no");

        Task task = new Task();
        task.setName("example");
        task.setDescription("example description");
        task.setAssignedTo(employee);
        task.setFinishBy(Date.valueOf("2026-04-15"));
        task.setCategory("GENERAL");
        task.setStatus("PENDING");
        task.setRecurring(false);
        task.setRecurringFrequency(null);

        taskRepository.save(task);

        Task[] tasks = taskRepository.findByEmployeeId(employee.getEmployeeId());

        boolean found = false;
        for (Task t : tasks) {
            if ("example".equals(t.getName())) {
                found = true;
                break;
            }
        }

        assertTrue(found);
    }

    @Test
    @DisplayName("update should change existing task")
    void updateTaskTest() {
        Employee employee = employeeRepository.findByEmail("ola@test.no");
        Task[] tasks = taskRepository.findByEmployeeId(employee.getEmployeeId());
        Task task = tasks[0];

        task.setName("Updated task");
        task.setStatus("DONE");

        taskRepository.update(task);

        Task updated = taskRepository.findById(task.getTaskId());

        assertEquals("Updated task", updated.getName());
        assertEquals("DONE", updated.getStatus());
    }
}