package com.RESTurantManager.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.RESTurantManager.demo.db.interfaces.EmployeeRepository;
import com.RESTurantManager.demo.model.Employee;

/**
 * Repository class for managing employees in the database.
 */
@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Saves a new employee to the database.
     * @param employee the employee to be saved
     */
    @Override
    public void save(Employee employee) {
        jdbcTemplate.update(
            "INSERT INTO employees (name, role, email, password, phone_number, resturant_id) VALUES (?, ?, ?, ?, ?, ?)",
            employee.getName(),
            employee.getRole(),
            employee.getEmail(),
            employee.getPassword(),
            employee.getPhoneNumber(),
            employee.getResturantId()
        );
    }

    /**
     * Finds an employee by their ID.
     * @param id the ID of the employee
     * @return the employee with the specified ID
     */
    @Override
    public Employee findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM employees WHERE employee_id = ?",
                new BeanPropertyRowMapper<>(Employee.class),
                id
        );
    }

    /**
     * Deletes an employee by their ID.
     * @param id the ID of the employee to be deleted
     */
    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM employees WHERE employee_id = ?", id);
    }

    /**
     * Finds an employee by their username.
     * @param username the username of the employee
     * @return the employee with the specified username
     */
    @Override
    public Employee findByUsername(String username) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM employees WHERE name = ?",
                new BeanPropertyRowMapper<>(Employee.class),
                username
        );
    }

    /**
     * Finds an employee by their email.
     * @param email the email of the employee
     * @return the employee with the specified email
     */
    @Override
    public Employee findByEmail(String email) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM employees WHERE email = ?",
                new BeanPropertyRowMapper<>(Employee.class),
                email
        );
    }

    /**
     * Finds employees by the ID of the associated restaurant.
     * @param resturantId the ID of the restaurant
     * @return an array of employees associated with the specified restaurant ID
     */
    @Override
    public Employee[] findByResturantId(int resturantId) {
        return jdbcTemplate.query(
                "SELECT * FROM employees WHERE resturant_id = ?",
                new BeanPropertyRowMapper<>(Employee.class),
                resturantId
        ).toArray(new Employee[0]);
    }
}
