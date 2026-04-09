package com.RESTurantManager.demo.db.interfaces;

import java.util.ArrayList;

import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.model.Resturant;

/**
 * Interface for managing restaurants in the database. Provides methods for retrieving employees and managers associated with a restaurant, adding and removing employees and managers from a restaurant, and retrieving restaurant information based on employee ID or restaurant ID.
 */
public interface ResturantRepository {
    /**
     * Retrieves all employees associated with a specific restaurant ID from the restaurant repository.
     * @param resturantId the ID of the restaurant whose employees are to be retrieved
     * @return an array of employees associated with the specified restaurant ID
     */
    ArrayList<Employee> getEmployeesByResturantId(int resturantId);

    /**
     * Retrieves all managers associated with a specific restaurant ID from the restaurant repository.
     * @param resturantId the ID of the restaurant whose managers are to be retrieved
     * @return an array of managers associated with the specified restaurant ID
     */
    ArrayList<Employee> getManagersByResturantId(int resturantId);

    /**
     * Adds an employee to a restaurant by updating the restaurant's employee list in the restaurant repository.
     * @param resturantId the ID of the restaurant to which the employee is to be added
     * @param employee the employee to be added to the restaurant
     */
    void addEmployeeToResturant(int resturantId, Employee employee);

    /**
     * Removes an employee from a restaurant by updating the restaurant's employee list in the restaurant repository.
     * @param resturantId the ID of the restaurant from which the employee is to be removed
     * @param employeeId the ID of the employee to be removed from the restaurant
     */
    void removeEmployeeFromResturant(int resturantId, int employeeId);

    /**
     * Adds a manager to a restaurant by updating the restaurant's manager list in the restaurant repository.
     * @param resturantId the ID of the restaurant to which the manager is to be added
     * @param manager the manager to be added to the restaurant
     */
    void addManagerToResturant(int resturantId, Employee manager);

    /**
     * Removes a manager from a restaurant by updating the restaurant's manager list in the restaurant repository.
     * @param resturantId the ID of the restaurant from which the manager is to be removed
     * @param managerId the ID of the manager to be removed from the restaurant
     */
    void removeManagerFromResturant(int resturantId, int managerId);

    /**
     * Retrieves a restaurant associated with a specific employee ID from the restaurant repository.
     * @param employeeId the ID of the employee whose restaurant is to be retrieved
     * @return the restaurant associated with the specified employee ID
     */
    Resturant getResturantByEmployeeId(int employeeId);

    /**
     * Retrieves a restaurant by its ID from the restaurant repository.
     * @param resturantId the ID of the restaurant to be retrieved
     * @return the restaurant with the specified ID
     */
    Resturant getResturantById(int resturantId);

    /**
     * Creates a new restaurant in the restaurant repository with the specified name, manager ID, and join code.
     * @param name the name of the restaurant to be created
     * @param managerId the ID of the manager associated with the restaurant to be created
     * @param joinCode the join code for the restaurant to be created
     * @return the ID of the newly created restaurant
     */
    int createResturant(String name, int managerId, String joinCode);

    /**
     * Retrieves a restaurant ID associated with a specific join code from the restaurant repository.
     * @param joinCode the join code of the restaurant whose ID is to be retrieved
     * @return the ID of the restaurant associated with the specified join code
     */
    int getResturantIdByJoinCode(String joinCode);
}