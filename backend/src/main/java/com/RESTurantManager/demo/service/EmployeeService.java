package com.RESTurantManager.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.EmployeeRepository;
import com.RESTurantManager.demo.db.requests.LoginRequest;
import com.RESTurantManager.demo.db.responses.LoginResponse;
import com.RESTurantManager.demo.model.Employee;

/**
 * Service class for handling employee-related operations, such as creating, retrieving, updating, and deleting employees.
 */
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final AuthenticationService authenticationService;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor for EmployeeService. Initializes the employee repository, authentication service, and password encoder for managing employee data and authentication.
     * @param employeeRepository the repository for managing employee data
     * @param authenticationService the service for handling authentication operations
     * @param passwordEncoder the encoder for hashing employee passwords
     */
    public EmployeeService(EmployeeRepository employeeRepository, AuthenticationService authenticationService, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.authenticationService = authenticationService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a new employee by saving it to the employee repository. The employee's password is hashed before being saved.
     * @param employee the employee to be created
     * @throws IllegalArgumentException if the employee's password is null or empty
     */
    public void createEmployee(Employee employee) {
        if(employee.getPassword() == null || employee.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employeeRepository.save(employee);
    }

    /**
     * Retrieves an employee by its ID from the employee repository.
     * @param id the ID of the employee to be retrieved
     * @return the employee with the specified ID
     */
    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    /**
     * Deletes an employee by its ID from the employee repository.
     * @param id the ID of the employee to be deleted
     */
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

    /**
     * Handles the login process for an employee. Validates the provided email and password, and returns a login response containing a JWT token if the login is successful.
     * @param loginRequest the request containing the employee's email and password
     * @return a LoginResponse containing the result of the login attempt, including a JWT token if successful
     * @throws IllegalArgumentException if the user is not found or if the password is incorrect
     */
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

    /**
     * Retrieves all employees associated with a specific restaurant ID from the employee repository.
     * @param resturantId the ID of the restaurant whose employees are to be retrieved
     * @return an array of employees associated with the specified restaurant ID
     */
    public Employee[] getEmployeesByResturantId(int resturantId) {
        return employeeRepository.findByResturantId(resturantId);
    }
}
