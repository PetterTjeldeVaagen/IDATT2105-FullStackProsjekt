package com.RESTurantManager.demo.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RESTurantManager.demo.db.requests.ResturantRequest;
import com.RESTurantManager.demo.db.responses.ResturantResponse;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.security.JoinCodeGenerator;
import com.RESTurantManager.demo.service.EmployeeService;
import com.RESTurantManager.demo.service.ResturantService;

/**
 * Controller for handling restaurant related endpoints such as creating a restaurant, adding/removing employees and managers, and retrieving restaurant information.
 */
@RestController
@RequestMapping("/resturant")
@CrossOrigin(origins = "http://localhost:5173")
public class ResturantController {
    private final ResturantService resturantService;
    private final EmployeeService employeeService;

    /**
     * Constructor for ResturantController.
     * @param resturantService the service for managing restaurant operations
     * @param employeeService the service for managing employee operations
     */
    public ResturantController(ResturantService resturantService, EmployeeService employeeService) {
        this.resturantService = resturantService;
        this.employeeService = employeeService;
    }

    /**
     * Endpoint for retrieving employees by restaurant ID.
     * @param resturantId the ID of the restaurant whose employees are to be retrieved
     * @return ResponseEntity containing the retrieved employees response
     */
    @GetMapping("/getEmployees/{resturantId}")
    public ResponseEntity<ArrayList<Employee>> getEmployeesByResturantId(@PathVariable int resturantId) {
        ArrayList<Employee> employees = resturantService.getEmployeesByResturantId(resturantId);
        return ResponseEntity.ok(employees);
    }

    /**
     * Endpoint for retrieving managers by restaurant ID.
     * @param resturantId the ID of the restaurant whose managers are to be retrieved
     * @return ResponseEntity containing the retrieved managers response
     */
    @GetMapping("/getManagers/{resturantId}")
    public ResponseEntity<ArrayList<Employee>> getManagersByResturantId(@PathVariable int resturantId) {
        ArrayList<Employee> managers = resturantService.getManagersByResturantId(resturantId);
        return ResponseEntity.ok(managers);
    }
    
    /**
     * Endpoint for creating a new restaurant.
     * @param resturantRequest the request containing restaurant details
     * @return ResponseEntity indicating the result of the create operation
     */
    @PostMapping("/createResturant")
    public ResponseEntity<Void> createResturant(@RequestBody ResturantRequest resturantRequest) {
        String joinCode = JoinCodeGenerator.generateJoinCode();
        resturantService.createResturant(resturantRequest.getName(), resturantRequest.getEmployeeId(), joinCode);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint for adding an employee to a restaurant.
     * @param resturantRequest the request containing restaurant ID and employee ID
     * @return ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/addEmployee")
    public ResponseEntity<Void> addEmployeeToResturant(@RequestBody ResturantRequest resturantRequest) {
        resturantService.addEmployeeToResturant(resturantRequest.getResturantId(), employeeService.getEmployeeById(resturantRequest.getEmployeeId()));
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint for removing an employee from a restaurant.
     * @param resturantRequest the request containing restaurant ID and employee ID
     * @return ResponseEntity indicating the result of the remove operation
     */
    @PostMapping("/removeEmployee")
    public ResponseEntity<Void> removeEmployeeFromResturant(@RequestBody ResturantRequest resturantRequest) {
        resturantService.removeEmployeeFromResturant(resturantRequest.getResturantId(), resturantRequest.getEmployeeId());
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint for adding a manager to a restaurant.
     * @param resturantRequest the request containing restaurant ID and manager ID
     * @return ResponseEntity indicating the result of the add operation
     */
    @PostMapping("/addManager")
    public ResponseEntity<Void> addManagerToResturant(@RequestBody ResturantRequest resturantRequest) {
        resturantService.addManagerToResturant(resturantRequest.getResturantId(), employeeService.getEmployeeById(resturantRequest.getEmployeeId()));
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint for removing a manager from a restaurant.
     * @param resturantRequest the request containing restaurant ID and manager ID
     * @return ResponseEntity indicating the result of the remove operation
     */
    @PostMapping("/removeManager")
    public ResponseEntity<Void> removeManagerFromResturant(@RequestBody ResturantRequest resturantRequest) {
        resturantService.removeManagerFromResturant(resturantRequest.getResturantId(), resturantRequest.getEmployeeId());
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint for retrieving a restaurant by employee ID.
     * @param employeeId the ID of the employee whose restaurant is to be retrieved
     * @return ResponseEntity containing the retrieved restaurant response
     */
    @GetMapping("/getResturantByEmployeeId/{employeeId}")
    public ResponseEntity<ResturantResponse> getResturantByEmployeeId(@PathVariable int employeeId) {
        ResturantResponse resturantResponse = resturantService.getResturantByEmployeeId(employeeId);
        return ResponseEntity.ok(resturantResponse);
    }

    /**
     * Endpoint for retrieving a restaurant by its ID.
     * @param resturantId the ID of the restaurant to be retrieved
     * @return ResponseEntity containing the retrieved restaurant response
     */
    @GetMapping("/getResturant/{resturantId}")
    public ResponseEntity<ResturantResponse> getResturantById(@PathVariable int resturantId) {
        ResturantResponse resturantResponse = resturantService.getResturantById(resturantId);
        return ResponseEntity.ok(resturantResponse);
    }

    /**
     * Endpoint for joining a restaurant using a join code.
     * @param joinCode the join code for the restaurant
     * @param employeeId the ID of the employee joining the restaurant
     * @return ResponseEntity indicating the result of the join operation
     */
    @PostMapping("/joinResturant/{joinCode}/{employeeId}")
    public ResponseEntity<Void> joinResturant(@PathVariable String joinCode, @PathVariable int employeeId) {
        resturantService.joinResturant(joinCode, employeeId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint for retrieving the join code of a restaurant by its ID.
     * @param resturantId the ID of the restaurant whose join code is to be retrieved
     * @return ResponseEntity containing the retrieved join code
     */
    @GetMapping("/getJoinCode/{resturantId}")
    public ResponseEntity<String> getJoinCode(@PathVariable int resturantId) {
        ResturantResponse resturantResponse = resturantService.getResturantById(resturantId);
        return ResponseEntity.ok(resturantResponse.getJoinCode());
    }
}
