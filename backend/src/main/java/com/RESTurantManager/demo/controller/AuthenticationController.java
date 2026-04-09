package com.RESTurantManager.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RESTurantManager.demo.db.requests.LoginRequest;
import com.RESTurantManager.demo.db.requests.RegisterRequest;
import com.RESTurantManager.demo.db.responses.LoginResponse;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.service.EmployeeService;

import jakarta.validation.Valid;

/**
 * Controller for handling authentication related endpoints such as login and registration.
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthenticationController {
    private final EmployeeService employeeService;

    /**
     * Constructor for AuthenticationController.
     * @param employeeService the service for managing employee operations
     */
    public AuthenticationController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Endpoint for user login.
     * @param loginRequest the login request containing user credentials
     * @return ResponseEntity containing the login response
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            LoginResponse response = employeeService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Invalid credentials"));
        }
    }

    /**
     * Endpoint for user registration.
     * @param registerRequest the registration request containing user details
     * @return ResponseEntity containing the login response after successful registration
     */
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            Employee employee = new Employee();
            employee.setName(registerRequest.getUsername());
            employee.setPassword(registerRequest.getPassword());
            employee.setEmail(registerRequest.getEmail());
            employee.setRole("EMPLOYEE");
            
            employeeService.createEmployee(employee);
            LoginRequest loginRequest = new LoginRequest(registerRequest.getPassword(), registerRequest.getEmail());
            LoginResponse response = employeeService.login(loginRequest);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new LoginResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new LoginResponse("Registration failed"));
        }
    }
}
