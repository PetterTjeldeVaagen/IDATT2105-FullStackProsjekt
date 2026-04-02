package com.RESTurantManager.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RESTurantManager.demo.db.requests.DeviationRequest;
import com.RESTurantManager.demo.db.responses.DeviationResponse;
import com.RESTurantManager.demo.model.Deviation;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.service.DeviationService;
import com.RESTurantManager.demo.service.EmployeeService;

@RestController
@RequestMapping("/deviation")
@CrossOrigin(origins = "http://localhost:5173")
public class DeviationController {
    private final DeviationService deviationService;
    private final EmployeeService employeeService;

    public DeviationController(DeviationService deviationService, EmployeeService employeeService) {
        this.deviationService = deviationService;
        this.employeeService = employeeService;
    }

    @PutMapping("/createDeviation") 
    public ResponseEntity<DeviationResponse> createDeviation(@RequestBody DeviationRequest deviationRequest) {
        Employee employee = employeeService.getEmployeeById(deviationRequest.getRegisteredBy());
        Deviation deviation = new Deviation(deviationRequest.getTitle(),deviationRequest.getDeviationId(), deviationRequest.getDescription(), 
                                            employee, deviationRequest.getDate());
        deviationService.createDeviation(deviation);
        DeviationResponse deviationResponse = new DeviationResponse(deviation.getDescription(), deviation.getDateRegistered(), deviation.getName(), 
                                                                    deviation.getRegisteredBy().getEmployeeId(), deviation.getDeviationId());
        return ResponseEntity.ok(deviationResponse);
    }

    @PutMapping("/deleteDeviation")
    public ResponseEntity<DeviationResponse> deleteDeviation(@RequestBody int deviationId) {
        deviationService.deleteDeviationById(deviationId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getDeviation")
    public ResponseEntity<DeviationResponse> getDeviation(@RequestBody int deviationId) {
        Deviation deviation = deviationService.getDeviationById(deviationId);
        DeviationResponse deviationResponse = new DeviationResponse(deviation.getDescription(), deviation.getDateRegistered(), deviation.getName(), 
                                                                    deviation.getRegisteredBy().getEmployeeId(), deviation.getDeviationId());
        return ResponseEntity.ok(deviationResponse);
    }
}
