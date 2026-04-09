package com.RESTurantManager.demo.controller;

import java.util.ArrayList;

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
import org.springframework.security.test.context.support.WithMockUser;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.RESTurantManager.demo.db.requests.ResturantRequest;
import com.RESTurantManager.demo.db.responses.ResturantResponse;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.security.JwtAuthorizationFilter;
import com.RESTurantManager.demo.service.EmployeeService;
import com.RESTurantManager.demo.service.ResturantService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(
    controllers = ResturantController.class,
    excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthorizationFilter.class)
)
class ResturantControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ResturantService resturantService;

    @MockitoBean
    private EmployeeService employeeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("getEmployeesByResturantId should return list of employees for given restaurant")
    void getEmployeesByResturantIdTest() throws Exception {
        Employee employee1 = new Employee();
        employee1.setEmployeeId(1);
        employee1.setName("Ola");
        employee1.setEmail("ola@test.no");

        Employee employee2 = new Employee();
        employee2.setEmployeeId(2);
        employee2.setName("Kari");
        employee2.setEmail("kari@test.no");

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        when(resturantService.getEmployeesByResturantId(1)).thenReturn(employees);

        mockMvc.perform(get("/resturant/getEmployees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeId").value(1))
                .andExpect(jsonPath("$[0].name").value("Ola"))
                .andExpect(jsonPath("$[1].employeeId").value(2))
                .andExpect(jsonPath("$[1].name").value("Kari"));

        verify(resturantService).getEmployeesByResturantId(1);
    }

    @Test
    @DisplayName("getManagersByResturantId should return list of managers for given restaurant")
    void getManagersByResturantIdTest() throws Exception {
        Employee manager = new Employee();
        manager.setEmployeeId(2);
        manager.setName("Manager");
        manager.setEmail("manager@test.no");

        ArrayList<Employee> managers = new ArrayList<>();
        managers.add(manager);

        when(resturantService.getManagersByResturantId(1)).thenReturn(managers);

        mockMvc.perform(get("/resturant/getManagers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].employeeId").value(2))
                .andExpect(jsonPath("$[0].name").value("Manager"));

        verify(resturantService).getManagersByResturantId(1);
    }

    @Test
    @DisplayName("createResturant should return 200")
    void createResturantTest() throws Exception {
        ResturantRequest request = new ResturantRequest();
        request.setName("New Resturant");
        request.setEmployeeId(2);

        mockMvc.perform(post("/resturant/createResturant")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(resturantService).createResturant(any(String.class), any(Integer.class), any(String.class));
    }

    @Test
    @DisplayName("addEmployeeToResturant should return 200")
    void addEmployeeToResturantTest() throws Exception {
        ResturantRequest request = new ResturantRequest();
        request.setResturantId(1);
        request.setEmployeeId(3);

        Employee employee = new Employee();
        employee.setEmployeeId(3);
        employee.setName("New Employee");

        when(employeeService.getEmployeeById(3)).thenReturn(employee);

        mockMvc.perform(post("/resturant/addEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(employeeService).getEmployeeById(3);
        verify(resturantService).addEmployeeToResturant(1, employee);
    }

    @Test
    @DisplayName("removeEmployeeFromResturant should return 200")
    @WithMockUser(roles = "MANAGER")
    void removeEmployeeFromResturantTest() throws Exception {
        ResturantRequest request = new ResturantRequest();
        request.setResturantId(1);
        request.setEmployeeId(3);

        mockMvc.perform(post("/resturant/removeEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(resturantService).removeEmployeeFromResturant(1, 3);
    }

    @Test
    @DisplayName("addManagerToResturant should return 200")
    @WithMockUser(roles = "MANAGER")
    void addManagerToResturantTest() throws Exception {
        ResturantRequest request = new ResturantRequest();
        request.setResturantId(1);
        request.setEmployeeId(4);

        Employee manager = new Employee();
        manager.setEmployeeId(4);
        manager.setName("New Manager");

        when(employeeService.getEmployeeById(4)).thenReturn(manager);

        mockMvc.perform(post("/resturant/addManager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(employeeService).getEmployeeById(4);
        verify(resturantService).addManagerToResturant(1, manager);
    }

    @Test
    @DisplayName("removeManagerFromResturant should return 200")
    @WithMockUser(roles = "MANAGER")
    void removeManagerFromResturantTest() throws Exception {
        ResturantRequest request = new ResturantRequest();
        request.setResturantId(1);
        request.setEmployeeId(4);

        mockMvc.perform(post("/resturant/removeManager")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(resturantService).removeManagerFromResturant(1, 4);
    }

    @Test
    @DisplayName("getResturantByEmployeeId should return restaurant")
    void getResturantByEmployeeIdTest() throws Exception {
        ResturantResponse response = new ResturantResponse("Test Resturant");
        response.setResturantId(1);
        response.setJoinCode("join123");

        when(resturantService.getResturantByEmployeeId(10)).thenReturn(response);

        mockMvc.perform(get("/resturant/getResturantByEmployeeId/10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Resturant"))
                .andExpect(jsonPath("$.resturantId").value(1))
                .andExpect(jsonPath("$.joinCode").value("join123"));

        verify(resturantService).getResturantByEmployeeId(10);
    }

    @Test
    @DisplayName("getResturantById should return restaurant")
    void getResturantByIdTest() throws Exception {
        ResturantResponse response = new ResturantResponse("Test Resturant");
        response.setResturantId(1);
        response.setJoinCode("join123");

        when(resturantService.getResturantById(1)).thenReturn(response);

        mockMvc.perform(get("/resturant/getResturant/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Resturant"))
                .andExpect(jsonPath("$.resturantId").value(1))
                .andExpect(jsonPath("$.joinCode").value("join123"));

        verify(resturantService).getResturantById(1);
    }

    @Test
    @DisplayName("joinResturant should return 200")
    void joinResturantTest() throws Exception {
        mockMvc.perform(post("/resturant/joinResturant/join123/1"))
                .andExpect(status().isOk());

        verify(resturantService).joinResturant("join123", 1);
    }

    @Test
    @DisplayName("getJoinCode should return join code")
    void getJoinCodeTest() throws Exception {
        ResturantResponse response = new ResturantResponse("Test Resturant");
        response.setResturantId(1);
        response.setJoinCode("join123");

        when(resturantService.getResturantById(1)).thenReturn(response);

        mockMvc.perform(get("/resturant/getJoinCode/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("join123"));

        verify(resturantService).getResturantById(1);
    }
}