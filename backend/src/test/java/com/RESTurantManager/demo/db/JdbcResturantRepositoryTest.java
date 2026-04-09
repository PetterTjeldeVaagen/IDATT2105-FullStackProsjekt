package com.RESTurantManager.demo.db;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.RESTurantManager.demo.db.interfaces.EmployeeRepository;
import com.RESTurantManager.demo.db.interfaces.ResturantRepository;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.model.Resturant;

@SpringBootTest
@Transactional
class JdbcResturantRepositoryTest {

    @Autowired
    private ResturantRepository resturantRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("getResturantIdByJoinCode should return restaurant id")
    void getResturantIdByJoinCodeTest() {
        int resturantId = resturantRepository.getResturantIdByJoinCode("1234567890");

        assertTrue(resturantId > 0);
    }

    @Test
    @DisplayName("getResturantByEmployeeId should return restaurant for employee")
    void getResturantByEmployeeIdTest() {
        Employee employee = employeeRepository.findByEmail("ola@test.no");

        Resturant resturant = resturantRepository.getResturantByEmployeeId(employee.getEmployeeId());

        assertNotNull(resturant);
        assertEquals("Egon Trondheim", resturant.getName());
    }

    @Test
    @DisplayName("getManagersByResturantId should return only managers")
    void getManagersByResturantIdTest() {
        ArrayList<Employee> managers = resturantRepository.getManagersByResturantId(1);

        assertNotNull(managers);
        assertTrue(managers.size() >= 1);
        assertTrue(managers.stream().allMatch(e -> "MANAGER".equals(e.getRole())));
    }

    @Test
    @DisplayName("addEmployeeToResturant should update employee restaurant id")
    void addEmployeeToResturantTest() {
        Employee employee = new Employee();
        employee.setName("example");
        employee.setEmail("example@test.no");
        employee.setPassword("password");
        employee.setRole("EMPLOYEE");
        employee.setPhoneNumber("12345678");
        employee.setResturantId(2);

        employeeRepository.save(employee);
        Employee saved = employeeRepository.findByEmail("example@test.no");

        resturantRepository.addEmployeeToResturant(1, saved);

        Employee updated = employeeRepository.findByEmail("example@test.no");
        assertEquals(1, updated.getResturantId());
    }
}