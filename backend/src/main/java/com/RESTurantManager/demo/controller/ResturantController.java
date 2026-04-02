package com.RESTurantManager.demo.controller;

import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RESTurantManager.demo.db.requests.ResturantRequest;
import com.RESTurantManager.demo.db.responses.ResturantResponse;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.service.EmployeeService;
import com.RESTurantManager.demo.service.ResturantService;

@RestController
@RequestMapping("/resturant")
@CrossOrigin(origins = "http://localhost:5173")
public class ResturantController {
    private final ResturantService resturantService;
    private final EmployeeService employeeService;

    public ResturantController(ResturantService resturantService, EmployeeService employeeService) {
        this.resturantService = resturantService;
        this.employeeService = employeeService;
    }

    //rsturant: String name, int resturantId, Employee manager
    //resturant response: String name, int resturantId

    @GetMapping("/getEmployees")
    public ResponseEntity<ArrayList<Employee>> getEmployeesByResturantId(@RequestBody ResturantRequest resturantRequest) {
        ArrayList<Employee> employees = resturantService.getEmployeesByResturantId(resturantRequest.getResturantId());
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/getManagers")
    public ResponseEntity<ArrayList<Employee>> getManagersByResturantId(@RequestBody ResturantRequest resturantRequest) {
        ArrayList<Employee> managers = resturantService.getManagersByResturantId(resturantRequest.getResturantId());
        return ResponseEntity.ok(managers);
    }
    
    @PutMapping("/createResturant")
    public ResponseEntity<Void> createResturant(@RequestBody ResturantRequest resturantRequest) {
        resturantService.createResturant(resturantRequest.getName(), resturantRequest.getResturantId(), resturantRequest.getManagerId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/addEmployee")
    public ResponseEntity<Void> addEmployeeToResturant(@RequestBody ResturantRequest resturantRequest) {
        resturantService.addEmployeeToResturant(resturantRequest.getResturantId(), employeeService.getEmployeeById(resturantRequest.getManagerId()));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/removeEmployee")
    public ResponseEntity<Void> removeEmployeeFromResturant(@RequestBody ResturantRequest resturantRequest) {
        resturantService.removeEmployeeFromResturant(resturantRequest.getResturantId(), resturantRequest.getManagerId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/addManager")
    public ResponseEntity<Void> addManagerToResturant(@RequestBody ResturantRequest resturantRequest) {
        resturantService.addManagerToResturant(resturantRequest.getResturantId(), employeeService.getEmployeeById(resturantRequest.getManagerId()));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/removeManager")
    public ResponseEntity<Void> removeManagerFromResturant(@RequestBody ResturantRequest resturantRequest) {
        resturantService.removeManagerFromResturant(resturantRequest.getResturantId(), resturantRequest.getManagerId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getResturantIdByEmployeeId")
    public ResponseEntity<ResturantResponse> getResturantIdByEmployeeId(@RequestBody ResturantRequest resturantRequest) {
        int resturantId = resturantService.getResturantIdByEmployeeId(resturantRequest.getManagerId());
        ResturantResponse resturantResponse = new ResturantResponse(resturantRequest.getName(), resturantId);
        return ResponseEntity.ok(resturantResponse);
    }
}
