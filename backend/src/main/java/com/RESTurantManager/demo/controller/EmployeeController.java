package com.RESTurantManager.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RESTurantManager.demo.db.requests.EmployeeRequest;
import com.RESTurantManager.demo.db.responses.EmployeeResponse;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.service.EmployeeService;

/**
 * Controller for handling employee related endpoints such as creating, updating, deleting and retrieving employees.
 */
@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
    private final EmployeeService employeeService;

    /**
     * Constructor for EmployeeController.
     * @param employeeService the service for managing employee operations
     */
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * Endpoint for creating a new employee.
     * @param employeeRequest the request containing employee details
     * @return ResponseEntity containing the created employee response
     */
    @PostMapping("/createEmployee")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getEmployeeId(), employeeRequest.getEmail(), 
                                            employeeRequest.getPhoneNumber(), null, employeeRequest.getRole(), 
                                            employeeRequest.getPassword(), employeeRequest.getResturantId());
        employeeService.createEmployee(employee);
        EmployeeResponse employeeResponse = new EmployeeResponse(employee.getName(), employee.getEmail(), employee.getPhoneNumber(), 
                                                                 employee.getResturantId(), employee.getRole(), employee.getEmployeeId());
        return ResponseEntity.ok(employeeResponse);
    }

    /**
     * Endpoint for deleting an employee by its ID.
     * @param employeeId the ID of the employee to be deleted
     * @return ResponseEntity indicating the result of the delete operation
     */
    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployeeById(employeeId);
         return ResponseEntity.ok().build();
    }

    /**
     * Endpoint for retrieving an employee by its ID.
     * @param employeeId the ID of the employee to be retrieved
     * @return ResponseEntity containing the retrieved employee response
     */
    @GetMapping("/getEmployee/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        EmployeeResponse employeeResponse = new EmployeeResponse(employee.getName(), employee.getEmail(), employee.getPhoneNumber(), 
                                                                 employee.getResturantId(), employee.getRole(), employee.getEmployeeId());
        return ResponseEntity.ok(employeeResponse);
    }

    /**
     * Endpoint for retrieving employees by restaurant ID.
     * @param resturantId the ID of the restaurant whose employees are to be retrieved
     * @return ResponseEntity containing the retrieved employees response
     */
    @GetMapping("/getEmployeesByResturant/{resturantId}")
    public ResponseEntity<EmployeeResponse[]> getEmployeesByResturantId(@PathVariable int resturantId) {
        Employee[] employees = employeeService.getEmployeesByResturantId(resturantId);
        EmployeeResponse[] employeeResponses = new EmployeeResponse[employees.length];
        for (int i = 0; i < employees.length; i++) {
            Employee employee = employees[i];
            employeeResponses[i] = new EmployeeResponse(employee.getName(), employee.getEmail(), employee.getPhoneNumber(), 
                                                        employee.getResturantId(), employee.getRole(), employee.getEmployeeId());
        }
        return ResponseEntity.ok(employeeResponses);
    }
}
