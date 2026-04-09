package com.RESTurantManager.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RESTurantManager.demo.db.requests.DeviationRequest;
import com.RESTurantManager.demo.db.responses.DeviationResponse;
import com.RESTurantManager.demo.model.Deviation;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.service.DeviationService;
import com.RESTurantManager.demo.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controller for handling deviation related endpoints such as creating, updating, deleting and retrieving deviations.
 */
@RestController
@RequestMapping("/deviation")
@CrossOrigin(origins = "http://localhost:5173")
@Tag(name = "DeviationController", description = "Controller for handling deviation related endpoints such as creating, updating, deleting and retrieving deviations.")
public class DeviationController {
    private final DeviationService deviationService;
    private final EmployeeService employeeService;

    /**
     * Constructor for DeviationController.
     * @param deviationService the service for managing deviation operations
     * @param employeeService the service for managing employee operations
     */
    public DeviationController(DeviationService deviationService, EmployeeService employeeService) {
        this.deviationService = deviationService;
        this.employeeService = employeeService;
    }

    /**
     * Endpoint for creating a new deviation.
     * @param deviationRequest the request containing deviation details
     * @return ResponseEntity containing the created deviation response
     */
    @Operation(summary = "Create a new deviation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Deviation created successfully", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeviationResponse.class))),
        @ApiResponse(responseCode = "400", description = "Invalid deviation data", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeviationResponse.class)))
    })
    @PostMapping("/createDeviation") 
    public ResponseEntity<DeviationResponse> createDeviation(@RequestBody DeviationRequest deviationRequest) {
        Employee employee = employeeService.getEmployeeById(deviationRequest.getRegisteredBy());
        Deviation deviation = new Deviation(deviationRequest.getTitle(), deviationRequest.getDescription(), 
                                            employee, deviationRequest.getDate());
        deviationService.createDeviation(deviation);
        DeviationResponse deviationResponse = new DeviationResponse(deviation.getDescription(), deviation.getDateRegistered(), deviation.getName(), 
                                                                    deviation.getRegisteredBy().getEmployeeId(), deviation.getDeviationId());
        return ResponseEntity.ok(deviationResponse);
    }

    /**
     * Endpoint for deleting a deviation by its ID.
     * @param deviationId the ID of the deviation to be deleted
     * @return ResponseEntity indicating the result of the delete operation
     */
    @Operation(summary = "Delete a deviation by its ID")
    @PreAuthorize("hasRole('MANAGER')")
    @DeleteMapping("/deleteDeviation/{deviationId}")
    public ResponseEntity<DeviationResponse> deleteDeviation(@PathVariable int deviationId) {
        deviationService.deleteDeviationById(deviationId);
        return ResponseEntity.ok().build();
    }

    /**
     * Endpoint for retrieving a deviation by its ID.
     * @param deviationId the ID of the deviation to be retrieved
     * @return ResponseEntity containing the retrieved deviation response
     */
    @Operation(summary = "Get a deviation by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Deviation retrieved successfully", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeviationResponse.class))),
        @ApiResponse(responseCode = "404", description = "Deviation not found", 
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeviationResponse.class)))
    })

    @GetMapping("/getDeviation/{deviationId}")
    public ResponseEntity<DeviationResponse> getDeviation(@PathVariable int deviationId) {
        Deviation deviation = deviationService.getDeviationById(deviationId);
        DeviationResponse deviationResponse = new DeviationResponse(deviation.getDescription(), deviation.getDateRegistered(), deviation.getName(), 
                                                                    deviation.getRegisteredBy().getEmployeeId(), deviation.getDeviationId());
        return ResponseEntity.ok(deviationResponse);
    }

    /**
     * Endpoint for retrieving deviations by employee ID.
     * @param employeeId the ID of the employee whose deviations are to be retrieved
     * @return ResponseEntity containing the retrieved deviations response
     */
    @Operation(summary = "Get deviations by employee ID")
    @GetMapping("/getDeviationByEmployee/{employeeId}")
    public ResponseEntity<DeviationResponse[]> getDeviationsByEmployeeId(@PathVariable int employeeId) {
        Deviation[] deviations = deviationService.getDeviationsByEmployeeId(employeeId);
        DeviationResponse[] deviationResponses = new DeviationResponse[deviations.length];
        for (int i = 0; i < deviations.length; i++) {
            Deviation deviation = deviations[i];
            DeviationResponse deviationResponse = new DeviationResponse(deviation.getDescription(), deviation.getDateRegistered(), deviation.getName(), 
                                                                        deviation.getRegisteredBy().getEmployeeId(), deviation.getDeviationId());
            deviationResponses[i] = deviationResponse;
        }
        return ResponseEntity.ok(deviationResponses);
    }

    /**
     * Endpoint for retrieving deviations by restaurant ID.
     * @param resturantId the ID of the restaurant whose deviations are to be retrieved
     * @return ResponseEntity containing the retrieved deviations response
     */
    @Operation(summary = "Get deviations by restaurant ID")
    @GetMapping("/getDeviationByResturant/{resturantId}")
    public ResponseEntity<DeviationResponse[]> getDeviationsByResturantId(@PathVariable int resturantId) {
        Employee[] employees = employeeService.getEmployeesByResturantId(resturantId);
        ArrayList<Deviation> deviations = new ArrayList<>();
        for (Employee employee : employees) {
            Deviation[] employeeDeviations = deviationService.getDeviationsByEmployeeId(employee.getEmployeeId());
            deviations.addAll(Arrays.asList(employeeDeviations));
        }
        DeviationResponse[] deviationResponses = new DeviationResponse[deviations.size()];
        for (int i = 0; i < deviations.size(); i++) {
            Deviation deviation = deviations.get(i);
            DeviationResponse deviationResponse = new DeviationResponse(deviation.getDescription(), deviation.getDateRegistered(), deviation.getName(), 
                                                                        deviation.getRegisteredBy().getEmployeeId(), deviation.getDeviationId());
            deviationResponses[i] = deviationResponse;
        }
        return ResponseEntity.ok(deviationResponses);
    }

    /**
     * Endpoint for updating a deviation by its ID.
     * @param deviationId the ID of the deviation to be updated
     * @param deviationRequest the request containing updated deviation details
     * @return ResponseEntity indicating the result of the update operation
     */
    @Operation(summary = "Update a deviation by its ID")
    @PostMapping("/updateDeviation/{deviationId}")
    public ResponseEntity<DeviationResponse> updateDeviation(@PathVariable int deviationId, @RequestBody DeviationRequest deviationRequest) {
        Employee employee = employeeService.getEmployeeById(deviationRequest.getRegisteredBy());
        Deviation deviation = new Deviation(deviationRequest.getTitle(), deviationRequest.getDescription(), 
                                            employee, deviationRequest.getDate());
        deviation.setDeviationId(deviationId);
        deviationService.updateDeviation(deviation);
        DeviationResponse deviationResponse = new DeviationResponse(deviation.getDescription(), deviation.getDateRegistered(), deviation.getName(), 
                                                                    deviation.getRegisteredBy().getEmployeeId(), deviation.getDeviationId());
        return ResponseEntity.ok(deviationResponse);
    }
}
