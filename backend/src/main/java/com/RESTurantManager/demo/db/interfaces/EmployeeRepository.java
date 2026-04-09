package com.RESTurantManager.demo.db.interfaces;

import com.RESTurantManager.demo.model.Employee;
/**
 * Interface for managing employees in the database. Provides methods for saving, finding, and deleting employees, as well as retrieving employees by restaurant ID.
 */
public interface EmployeeRepository {
    /**
     * Saves a new employee to the database.
     * @param employee the employee to be saved
     */
    void save(Employee employee);

    /**
     * Finds an employee by their ID in the database.
     * @param id the ID of the employee to be found
     * @return the employee with the specified ID
     */
    Employee findById(int id);

    /**
     * Deletes an employee by their ID from the database.
     * @param id the ID of the employee to be deleted
     */
    void deleteById(int id);

    /**
     * Retrieves an employee by their username from the database.
     * @param username the username of the employee to be found
     * @return the employee with the specified username
     */
    Employee findByUsername(String username);

    /**
     * Retrieves an employee by their email from the database.
     * @param email the email of the employee to be found
     * @return the employee with the specified email
     */
    Employee findByEmail(String email);

    /**
     * Retrieves all employees associated with a specific restaurant ID from the database.
     * @param resturantId the ID of the restaurant whose employees are to be retrieved
     * @return an array of employees associated with the specified restaurant ID
     */
    Employee[] findByResturantId(int resturantId);
}
