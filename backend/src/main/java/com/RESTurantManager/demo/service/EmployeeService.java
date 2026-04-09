package com.RESTurantManager.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.EmployeeRepository;
import com.RESTurantManager.demo.db.requests.LoginRequest;
import com.RESTurantManager.demo.db.responses.LoginResponse;
import com.RESTurantManager.demo.model.Employee;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository, AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    public void createEmployee(Employee employee) {
        if(employee.getPassword() == null || employee.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
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

        if (!passwordEncoder.matches(password, existing.getPassword()) || existing == null) {
            throw new IllegalArgumentException("Wrong password or email");
        }

        return new LoginResponse(
            "Login successful",
            authenticationService.getJWTToken(existing.getEmail()),
            existing.getEmployeeId(),
            existing.getEmail(),
            existing.getName()
        );
    }

    public Employee[] getEmployeesByResturantId(int resturantId) {
        return employeeRepository.findByResturantId(resturantId);
    }
}
