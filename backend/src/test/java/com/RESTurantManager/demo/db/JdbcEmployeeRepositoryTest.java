package com.RESTurantManager.demo.db;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.RESTurantManager.demo.db.interfaces.EmployeeRepository;
import com.RESTurantManager.demo.model.Employee;

@SpringBootTest
@Transactional
class JdbcEmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("findByEmail should return seeded employee")
    void findByEmailTest() {
        Employee employee = employeeRepository.findByEmail("ola@test.no");

        assertNotNull(employee);
        assertEquals("Ola Nordmann", employee.getName());
        assertEquals("ola@test.no", employee.getEmail());
    }

    @Test
    @DisplayName("findByResturantId should return employees for restaurant")
    void findByResturantIdTest() {
        Employee[] employees = employeeRepository.findByResturantId(1);

        assertNotNull(employees);
        assertTrue(employees.length >= 1);
    }

    @Test
    @DisplayName("save should persist employee so it can be found by email")
    void saveEmployeeTest() {
        Employee employee = new Employee();
        employee.setName("example");
        employee.setEmail("example@test.no");
        employee.setPassword("password");
        employee.setRole("EMPLOYEE");
        employee.setPhoneNumber("99999999");
        employee.setResturantId(1);

        employeeRepository.save(employee);

        Employee saved = employeeRepository.findByEmail("example@test.no");

        assertNotNull(saved);
        assertEquals("example", saved.getName());
        assertEquals("example@test.no", saved.getEmail());
    }
}