package com.RESTurantManager.demo.db;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.RESTurantManager.demo.db.interfaces.ResturantRepository;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.model.Resturant;

/**
 * Repository class for managing restaurants in the database.
 */
@Repository
public class JdbcResturantRepository implements ResturantRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Retrieves all employees associated with a specific restaurant ID from the database.
     * @param resturantId the ID of the restaurant whose employees are to be retrieved
     * @return an array of employees associated with the specified restaurant ID
     */
    @Override
    public ArrayList<Employee> getEmployeesByResturantId(int resturantId) {
        ArrayList<Employee> employees = (ArrayList<Employee>) jdbcTemplate.query(
                "SELECT * FROM employees WHERE resturant_id = ?",
                new BeanPropertyRowMapper<>(Employee.class),
                resturantId
        );

        return employees;
    }

    /**
     * Retrieves the managers associated with a specific restaurant ID from the database.
     * @param resturantId the ID of the restaurant whose managers are to be retrieved
     * @return an array of managers associated with the specified restaurant ID
     */
    @Override
    public ArrayList<Employee> getManagersByResturantId(int resturantId) {
        ArrayList<Employee> managers = (ArrayList<Employee>) jdbcTemplate.query(
                "SELECT * FROM employees WHERE resturant_id = ? AND role = ?",
                new BeanPropertyRowMapper<>(Employee.class),
                resturantId,
                "MANAGER"
        );

        return managers;
    }

    /**
     * Creates a new restaurant in the database.
     * @param name the name of the restaurant
     * @param managerId the ID of the manager associated with the restaurant
     * @param joinCode the unique join code for the restaurant
     * @return the ID of the newly created restaurant
     */
    @Override
    public int createResturant(String name, int managerId, String joinCode) {
        jdbcTemplate.update(
            "INSERT INTO resturants (name, location, join_code) VALUES (?, ?, ?)",
            name,
            "Unknown",
            joinCode

        );
        return jdbcTemplate.queryForObject("SELECT resturant_id FROM resturants WHERE join_code = ?", Integer.class, joinCode);
    }

    /**
     * Adds an employee to a restaurant.
     * @param resturantId the ID of the restaurant
     * @param employee the employee to be added to the restaurant
     */
    @Override
    public void addEmployeeToResturant(int resturantId, Employee employee) {
        jdbcTemplate.update(
                "UPDATE employees SET resturant_id = ? WHERE employee_id = ?",
                resturantId,
                employee.getEmployeeId()
        );
    }

    /**
     * Removes an employee from a restaurant.
     * @param resturantId the ID of the restaurant
     * @param employeeId the ID of the employee to be removed from the restaurant
     */
    @Override
    public void removeEmployeeFromResturant(int resturantId, int employeeId) {
        jdbcTemplate.update(
                "UPDATE employees SET resturant_id = NULL WHERE employee_id = ? AND resturant_id = ?",
                employeeId,
                resturantId
        );
    }

    /**
     * Adds a manager to a restaurant.
     * @param resturantId the ID of the restaurant
     * @param manager the manager to be added to the restaurant
     */
    @Override
    public void addManagerToResturant(int resturantId, Employee manager) {
        jdbcTemplate.update(
                "UPDATE employees SET resturant_id = ?, role = ? WHERE employee_id = ?",
                resturantId,
                "MANAGER",
                manager.getEmployeeId()
        );
    }

    /**
     * Removes a manager from a restaurant.
     * @param resturantId the ID of the restaurant
     * @param managerId the ID of the manager to be removed from the restaurant
     */
    @Override
    public void removeManagerFromResturant(int resturantId, int managerId) {
        jdbcTemplate.update(
                "UPDATE employees SET resturant_id = NULL WHERE employee_id = ? AND resturant_id = ? AND role = ?",
                managerId,
                resturantId,
                "MANAGER"
        );
    }

    /**
     * Retrieves the restaurant associated with a specific employee ID.
     * @param employeeId the ID of the employee
     * @return the restaurant associated with the specified employee ID
     */
    @Override
    public Resturant getResturantByEmployeeId(int employeeId) {
        return jdbcTemplate.queryForObject(
                "SELECT r.* FROM resturants r JOIN employees e ON r.resturant_id = e.resturant_id WHERE e.employee_id = ?",
                new BeanPropertyRowMapper<>(Resturant.class),
                employeeId
        );
    }

    /**
     * Retrieves a restaurant by its ID.
     * @param resturantId the ID of the restaurant
     * @return the restaurant with the specified ID
     */
    @Override
    public Resturant getResturantById(int resturantId) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM resturants WHERE resturant_id = ?",
                new BeanPropertyRowMapper<>(Resturant.class),
                resturantId
        );
    }

    /**
     * Retrieves the restaurant associated with a specific join code.
     * @param joinCode the unique join code of the restaurant
     * @return the ID of the restaurant associated with the specified join code
     */
    @Override
    public int getResturantIdByJoinCode(String joinCode) {
        return jdbcTemplate.queryForObject(
                "SELECT resturant_id FROM resturants WHERE join_code = ?",
                Integer.class,
                joinCode
        );
    }

}