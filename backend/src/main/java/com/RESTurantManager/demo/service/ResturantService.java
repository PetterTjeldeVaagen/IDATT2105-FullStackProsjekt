package com.RESTurantManager.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.ResturantRepository;
import com.RESTurantManager.demo.db.responses.ResturantResponse;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.model.Resturant;

/**
 * Service class for handling restaurant-related operations, such as creating, retrieving, updating, and deleting restaurants, as well as managing employees and managers associated with restaurants.
 */
@Service
public class ResturantService {
    private final ResturantRepository resturantRepository;
    private final EmployeeService employeeService;
    
    /**
     * Constructor for ResturantService. Initializes the restaurant repository and employee service for managing restaurant data and associated employee operations.
     * @param resturantRepository the repository for managing restaurant data
     * @param employeeService the service for handling employee-related operations
     */
    public ResturantService(ResturantRepository resturantRepository, EmployeeService employeeService) {
        this.resturantRepository = resturantRepository;
        this.employeeService = employeeService;
    }

    /**
     * Retrieves all employees associated with a specific restaurant ID from the restaurant repository.
     * @param resturantId the ID of the restaurant whose employees are to be retrieved
     * @return an array of employees associated with the specified restaurant ID
     */
    public ArrayList<Employee> getEmployeesByResturantId(int resturantId) {
        return resturantRepository.getEmployeesByResturantId(resturantId);
    }

    /**
     * Retrieves all managers associated with a specific restaurant ID from the restaurant repository.
     * @param resturantId the ID of the restaurant whose managers are to be retrieved
     * @return an array of managers associated with the specified restaurant ID
     */
    public ArrayList<Employee> getManagersByResturantId(int resturantId) {
        return resturantRepository.getManagersByResturantId(resturantId);
    }

    /**
     * Adds an employee to a restaurant by updating the restaurant's employee list in the restaurant repository.
     * @param resturantId the ID of the restaurant to which the employee is to be added
     * @param employee the employee to be added to the restaurant
     */
    public void addEmployeeToResturant(int resturantId, Employee employee) {
        resturantRepository.addEmployeeToResturant(resturantId, employee);
    }

    /**
     * Removes an employee from a restaurant by updating the restaurant's employee list in the restaurant repository.
     * @param resturantId the ID of the restaurant from which the employee is to be removed
     * @param employeeId the ID of the employee to be removed from the restaurant
     */
    public void removeEmployeeFromResturant(int resturantId, int employeeId) {
        resturantRepository.removeEmployeeFromResturant(resturantId, employeeId);
    }

    /**
     * Adds a manager to a restaurant by updating the restaurant's manager list in the restaurant repository.
     * @param resturantId the ID of the restaurant to which the manager is to be added
     * @param manager the manager to be added to the restaurant
     */
    public void addManagerToResturant(int resturantId, Employee manager) {
        resturantRepository.addManagerToResturant(resturantId, manager);
    }

    /**
     * Removes a manager from a restaurant by updating the restaurant's manager list in the restaurant repository.
     * @param resturantId the ID of the restaurant from which the manager is to be removed
     * @param managerId the ID of the manager to be removed from the restaurant
     */
    public void removeManagerFromResturant(int resturantId, int managerId) {
        resturantRepository.removeManagerFromResturant(resturantId, managerId);
    }

    /**
     * Retrieves a restaurant associated with a specific employee ID from the restaurant repository.
     * @param employeeId the ID of the employee whose restaurant is to be retrieved
     * @return a ResturantResponse containing the details of the restaurant associated with the specified employee ID
     */
    public ResturantResponse getResturantByEmployeeId(int employeeId) {
        Resturant resturant = resturantRepository.getResturantByEmployeeId(employeeId);
        ResturantResponse resturantResponse = new ResturantResponse(resturant.getName());
        resturantResponse.setResturantId(resturant.getResturantId());
        resturantResponse.setJoinCode(resturant.getJoinCode());
        return resturantResponse;
    }

    /**
     * Creates a new restaurant by saving it to the restaurant repository. The restaurant is associated with a manager specified by the manager ID.
     * @param name the name of the restaurant to be created
     * @param managerId the ID of the manager to be associated with the restaurant
     * @param joinCode the join code for the restaurant, which can be used by employees to join the restaurant
     */
    public void createResturant(String name, int managerId, String joinCode) {
        int resturantId = resturantRepository.createResturant(name, managerId, joinCode);
        addManagerToResturant(resturantId, employeeService.getEmployeeById(managerId));
    }

    /**
     * Retrieves a restaurant by its ID from the restaurant repository.
     * @param resturantId the ID of the restaurant to be retrieved
     * @return a ResturantResponse containing the details of the restaurant with the specified ID
     */
    public ResturantResponse getResturantById(int resturantId) {
        Resturant resturant = resturantRepository.getResturantById(resturantId);
        ResturantResponse resturantResponse = new ResturantResponse(resturant.getName());
        resturantResponse.setResturantId(resturant.getResturantId());
        resturantResponse.setJoinCode(resturant.getJoinCode());
        return resturantResponse;
    }

    /**
     * Allows an employee to join a restaurant using a join code. The employee is added to the restaurant's employee list in the restaurant repository.
     * @param joinCode the join code for the restaurant that the employee wants to join
     * @param employeeId the ID of the employee who wants to join the restaurant
     */
    public void joinResturant(String joinCode, int employeeId) {
        int resturantId = resturantRepository.getResturantIdByJoinCode(joinCode);
        addEmployeeToResturant(resturantId, employeeService.getEmployeeById(employeeId));
    }

}
