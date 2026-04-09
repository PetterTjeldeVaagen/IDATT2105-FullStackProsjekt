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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.RESTurantManager.demo.db.requests.LoginRequest;
import com.RESTurantManager.demo.db.requests.RegisterRequest;
import com.RESTurantManager.demo.db.responses.LoginResponse;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.security.JwtAuthorizationFilter;
import com.RESTurantManager.demo.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(
    controllers = AuthenticationController.class,
    excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = JwtAuthorizationFilter.class)
)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("POST /auth/login should return 200 and login response when credentials are valid")
    void successfulLoginTest() throws Exception {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        LoginResponse response = new LoginResponse(
                "Login successful",
                "fake-jwt-token",
                1,
                "test@example.com",
                "Test User"
        );

        when(employeeService.login(any(LoginRequest.class))).thenReturn(response);

        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response").value("Login successful"))
                .andExpect(jsonPath("$.token").value("fake-jwt-token"))
                .andExpect(jsonPath("$.employeeId").value(1))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.username").value("Test User"));

        verify(employeeService).login(any(LoginRequest.class));
    }

    @Test
    @DisplayName("POST /auth/register should return 200 when registration succeeds")
    void successfulRegisterTest() throws Exception {
        RegisterRequest request = new RegisterRequest();
        request.setUsername("New User");
        request.setEmail("new@example.com");
        request.setPassword("password");

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());

        verify(employeeService).createEmployee(any(Employee.class));
    }
}