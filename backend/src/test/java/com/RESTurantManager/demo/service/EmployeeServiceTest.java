package com.RESTurantManager.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.RESTurantManager.demo.db.interfaces.EmployeeRepository;
import com.RESTurantManager.demo.db.requests.LoginRequest;
import com.RESTurantManager.demo.db.responses.LoginResponse;
import com.RESTurantManager.demo.model.Employee;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmail("test@example.com");
        employee.setPassword("password");
        employee.setName("Test User");
        employee.setResturantId(1);
        employee.setRole("MANAGER");
    }

    @Test
    public void testCreateEmployee() {
        when(passwordEncoder.encode(employee.getPassword())).thenReturn("encodedPassword");
        employeeService.createEmployee(employee);

        verify(employeeRepository).save(employee);
    }

    @Test
    public void testGetEmployeeById() {
        int employeeId = 1;
        when(employeeRepository.findById(employeeId)).thenReturn(employee);

        Employee result = employeeService.getEmployeeById(employeeId);

        assertEquals(employee, result);
        verify(employeeRepository).findById(employeeId);
    }

    @Test
    public void testDeleteEmployeeById() {
        int employeeId = 1;
        employeeService.deleteEmployeeById(employeeId);

        verify(employeeRepository).deleteById(employeeId);
    }

    @Test
    public void testLoginSuccess() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("password");

        employee.setPassword("encodedPassword");

        when(employeeRepository.findByEmail("test@example.com")).thenReturn(employee);
        when(passwordEncoder.matches("password", "encodedPassword")).thenReturn(true);
        when(authenticationService.getJWTToken("test@example.com", "MANAGER")).thenReturn("fake-jwt-token");

        LoginResponse response = employeeService.login(loginRequest);

        assertEquals("Login successful", response.getResponse());
        assertNotNull(response.getToken());
        assertEquals("fake-jwt-token", response.getToken());
        assertEquals(employee.getEmployeeId(), response.getEmployeeId());
        assertEquals(employee.getEmail(), response.getEmail());
        assertEquals(employee.getName(), response.getUsername());
    }

    @Test
    public void testLoginUserNotFound() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("wrong@example.com");
        loginRequest.setPassword("password");

        when(employeeRepository.findByEmail("wrong@example.com")).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.login(loginRequest);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void testLoginWrongPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("test@example.com");
        loginRequest.setPassword("wrongpassword");

        employee.setPassword("encodedPassword");

        when(employeeRepository.findByEmail("test@example.com")).thenReturn(employee);
        when(passwordEncoder.matches("wrongpassword", "encodedPassword")).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.login(loginRequest);
        });

        assertEquals("Wrong password or email", exception.getMessage());
        verify(authenticationService, never()).getJWTToken("test@example.com", "MANAGER");
    }

    @Test
    public void testLoginNullEmailAndPassword() {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail(null);
        loginRequest.setPassword(null);

        when(employeeRepository.findByEmail("")).thenReturn(null);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.login(loginRequest);
        });

        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void testGetEmployeesByResturantId() {
        int resturantId = 1;
        Employee[] employees = new Employee[] { employee };

        when(employeeRepository.findByResturantId(resturantId)).thenReturn(employees);

        Employee[] result = employeeService.getEmployeesByResturantId(resturantId);

        assertEquals(1, result.length);
        assertEquals(employee, result[0]);
        verify(employeeRepository).findByResturantId(resturantId);
    }
}

