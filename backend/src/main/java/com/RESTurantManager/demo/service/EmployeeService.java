package com.RESTurantManager.demo.service;

import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.EmployeeRepository;
import com.RESTurantManager.demo.db.requests.LoginRequest;
import com.RESTurantManager.demo.db.responses.LoginResponse;
import com.RESTurantManager.demo.model.Employee;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthenticationService authenticationService;

    public EmployeeService(EmployeeRepository employeeRepository, AuthenticationService authenticationService) {
        this.employeeRepository = employeeRepository;
        this.authenticationService = authenticationService;
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    public LoginResponse login(LoginRequest loginRequest) {
        String password = loginRequest.getPassword() == null ? "" : loginRequest.getPassword();
        String email = loginRequest.getEmail() == null ? "" : loginRequest.getEmail();

        Employee existing = employeeRepository.findByEmail(email);
        if (existing == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (!password.equals(existing.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }

        return new LoginResponse(
            "Login successful",
            authenticationService.getJWTToken(existing.getEmail()),
            existing.getEmployeeId(),
            existing.getEmail()
        );
    }
}
