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

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "http://localhost:5173")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/createEmployee")
    public ResponseEntity<EmployeeResponse> createEmployee(@RequestBody EmployeeRequest employeeRequest) {
        Employee employee = new Employee(employeeRequest.getName(), employeeRequest.getEmployeeId(), employeeRequest.getEmail(), 
                                            employeeRequest.getPhoneNumber(), null, employeeRequest.getRole(), 
                                            employeeRequest.getPassword(), employeeRequest.getResturantId());
        employeeService.createEmployee(employee);
        EmployeeResponse employeeResponse = new EmployeeResponse(employee.getName(), employee.getEmail(), employee.getPhoneNumber(), 
                                                                 employee.getResturantId(), employee.getRole(), employee.getEmployeeId(), employee.getPassword());
        return ResponseEntity.ok(employeeResponse);
    }

    @DeleteMapping("/deleteEmployee/{employeeId}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable int employeeId) {
        employeeService.deleteEmployeeById(employeeId);
         return ResponseEntity.ok().build();
    }

    @GetMapping("/getEmployee/{employeeId}")
    public ResponseEntity<EmployeeResponse> getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        EmployeeResponse employeeResponse = new EmployeeResponse(employee.getName(), employee.getEmail(), employee.getPhoneNumber(), 
                                                                 employee.getResturantId(), employee.getRole(), employee.getEmployeeId(), employee.getPassword());
        return ResponseEntity.ok(employeeResponse);
    }
}
