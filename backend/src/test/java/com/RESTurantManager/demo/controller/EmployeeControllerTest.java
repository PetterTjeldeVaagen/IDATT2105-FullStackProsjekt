package com.RESTurantManager.demo.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.RESTurantManager.demo.db.requests.EmployeeRequest;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.security.JwtAuthorizationFilter;
import com.RESTurantManager.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(
    controllers = EmployeeController.class,
    excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthorizationFilter.class)
)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("createEmployee should return created employee response")
    void createEmployeeTest() throws Exception {
        EmployeeRequest request = new EmployeeRequest();
        request.setName("Ola Nordmann");
        request.setEmail("ola@test.no");
        request.setPhoneNumber("12345678");
        request.setResturantId(1);
        request.setRole("EMPLOYEE");
        request.setEmployeeId(10);
        request.setPassword("pass123");

        mockMvc.perform(post("/employee/createEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ola Nordmann"))
                .andExpect(jsonPath("$.email").value("ola@test.no"))
                .andExpect(jsonPath("$.phoneNumber").value("12345678"))
                .andExpect(jsonPath("$.resturantId").value(1))
                .andExpect(jsonPath("$.role").value("EMPLOYEE"))
                .andExpect(jsonPath("$.employeeId").value(10));

        verify(employeeService).createEmployee(any(Employee.class));
    }

    @Test
    @DisplayName("deleteEmployee should return 200")
    void deleteEmployeeTest() throws Exception {
        mockMvc.perform(delete("/employee/deleteEmployee/1"))
                .andExpect(status().isOk());

        verify(employeeService).deleteEmployeeById(1);
    }

    @Test
    @DisplayName("getEmployee should return employee response")
    void getEmployeeTest() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeId(1);
        employee.setName("Ola Nordmann");
        employee.setEmail("ola@test.no");
        employee.setPhoneNumber("12345678");
        employee.setResturantId(1);
        employee.setRole("EMPLOYEE");
        employee.setPassword("pass123");

        when(employeeService.getEmployeeById(1)).thenReturn(employee);

        mockMvc.perform(get("/employee/getEmployee/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(1))
                .andExpect(jsonPath("$.name").value("Ola Nordmann"))
                .andExpect(jsonPath("$.email").value("ola@test.no"))
                .andExpect(jsonPath("$.resturantId").value(1))
                .andExpect(jsonPath("$.role").value("EMPLOYEE"));

        verify(employeeService).getEmployeeById(1);
    }

    @Test
    @DisplayName("getEmployeesByResturantId should return list of employees")
    void getEmployeesByResturantIdTest() throws Exception {
        Employee employee1 = new Employee();
        employee1.setEmployeeId(1);
        employee1.setName("Ola");
        employee1.setEmail("ola@test.no");
        employee1.setRole("EMPLOYEE");
        employee1.setResturantId(1);

        Employee employee2 = new Employee();
        employee2.setEmployeeId(2);
        employee2.setName("Kari");
        employee2.setEmail("kari@test.no");
        employee2.setRole("MANAGER");
        employee2.setResturantId(1);

        when(employeeService.getEmployeesByResturantId(1)).thenReturn(new Employee[] { employee1, employee2 });

        mockMvc.perform(get("/employee/getEmployeesByResturant/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeId").value(1))
                .andExpect(jsonPath("$[0].name").value("Ola"))
                .andExpect(jsonPath("$[1].employeeId").value(2))
                .andExpect(jsonPath("$[1].name").value("Kari"));

        verify(employeeService).getEmployeesByResturantId(1);
    }
}