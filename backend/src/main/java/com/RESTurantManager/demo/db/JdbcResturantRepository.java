package com.RESTurantManager.demo.db;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.RESTurantManager.demo.db.interfaces.ResturantRepository;
import com.RESTurantManager.demo.model.Employee;

@Repository
public class JdbcResturantRepository implements ResturantRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public ArrayList<Employee> getEmployeesByResturantId(int resturantId) {
        ArrayList<Employee> employees = (ArrayList<Employee>) jdbcTemplate.query(
                "SELECT * FROM employees WHERE resturant_id = ?",
                new BeanPropertyRowMapper<>(Employee.class),
                resturantId
        );

        return employees;
    }

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

    @Override
    public void addEmployeeToResturant(int resturantId, Employee employee) {
        jdbcTemplate.update(
                "UPDATE employees SET resturant_id = ? WHERE employee_id = ?",
                resturantId,
                employee.getEmployeeId()
        );
    }

    @Override
    public void removeEmployeeFromResturant(int resturantId, int employeeId) {
        jdbcTemplate.update(
                "UPDATE employees SET resturant_id = NULL WHERE employee_id = ? AND resturant_id = ?",
                employeeId,
                resturantId
        );
    }

    @Override
    public void addManagerToResturant(int resturantId, Employee manager) {
        jdbcTemplate.update(
                "UPDATE employees SET resturant_id = ?, role = ? WHERE employee_id = ?",
                resturantId,
                "MANAGER",
                manager.getEmployeeId()
        );
    }

    @Override
    public void removeManagerFromResturant(int resturantId, int managerId) {
        jdbcTemplate.update(
                "UPDATE employees SET resturant_id = NULL WHERE employee_id = ? AND resturant_id = ? AND role = ?",
                managerId,
                resturantId,
                "MANAGER"
        );
    }

    @Override
    public int getResturantIdByEmployeeId(int employeeId) {
        return jdbcTemplate.queryForObject(
                "SELECT resturant_id FROM employees WHERE employee_id = ?",
                Integer.class,
                employeeId
        );
    }
}