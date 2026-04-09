package com.RESTurantManager.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.RESTurantManager.demo.model.Employee;

@ExtendWith(MockitoExtension.class)
public class AuthenticationServiceTest {

    private AuthenticationService authenticationService;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        authenticationService = new AuthenticationService();

        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmail("test@example.com");
        employee.setPassword("password");
        employee.setName("Test User");
        employee.setResturantId(1);
        employee.setRole("MANAGER");
    }

    @Test
    public void testGetJWTToken() {
        String email = employee.getEmail();
        String token = authenticationService.getJWTToken(email);
        assertNotNull(token);
        assertFalse(token.isEmpty());
    }

    @Test
    public void testValidateToken() {
        String email = employee.getEmail();
        String token = authenticationService.getJWTToken(email);
        assertTrue(authenticationService.validateToken(token));
    }

    @Test
    public void testValidateTokenWithInvalidToken() {
        String invalidToken = "invalid.token.here";
        assertFalse(authenticationService.validateToken(invalidToken));
    }

    @Test
    public void testGetEmailFromToken() {
        String expectedEmail = employee.getEmail();
        String token = authenticationService.getJWTToken(expectedEmail);
        String actualEmail = authenticationService.getEmailFromToken(token);

        assertEquals(expectedEmail, actualEmail);
    }
}
