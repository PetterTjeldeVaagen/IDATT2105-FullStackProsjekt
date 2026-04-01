package com.RESTurantManager.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RESTurantManager.demo.db.requests.EmployeeRequest;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PutMapping("/createEmployee")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getEmployeeId(), employeeRequest.getEmail(), 
                                            employeeRequest.getPhoneNumber(), null, employeeRequest.getRole(), 
                                            employeeRequest.getPassword(), employeeRequest.getResturantId());
        employeeService.createEmployee(employee);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/deleteEmployee")
    public ResponseEntity<Void> deleteEmployee(@RequestBody EmployeeRequest employeeRequest) {
        employeeService.deleteEmployeeById(employeeRequest.getEmployeeId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getEmployee")
    public ResponseEntity<Employee> getEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = employeeService.getEmployeeById(employeeRequest.getEmployeeId());
        return ResponseEntity.ok(employee);
    }
}
