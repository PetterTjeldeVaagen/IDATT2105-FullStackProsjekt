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
                "SELECT e.* FROM employees e JOIN resturant_employees re ON e.employee_id = re.employee_id WHERE re.resturant_id = ?",
                new BeanPropertyRowMapper<>(Employee.class),
                resturantId
        );

        return employees;
    }

    @Override
    public ArrayList<Employee> getManagersByResturantId(int resturantId) {
        ArrayList<Employee> managers = (ArrayList<Employee>) jdbcTemplate.query(
                "SELECT e.* FROM employees e JOIN resturant_managers rm ON e.employee_id = rm.employee_id WHERE rm.resturant_id = ?",
                new BeanPropertyRowMapper<>(Employee.class),
                resturantId
        );

        return managers;
    }

    @Override
    public void addEmployeeToResturant(int resturantId, Employee employee) {
        jdbcTemplate.update(
                "INSERT INTO resturant_employees (resturant_id, employee_id) VALUES (?, ?)",
                resturantId,
                employee.getEmployeeId()
        );
    }

    @Override
    public void removeEmployeeFromResturant(int resturantId, int employeeId) {
        jdbcTemplate.update(
                "DELETE FROM resturant_employees WHERE resturant_id = ? AND employee_id = ?",
                resturantId,
                employeeId
        );
    }

    @Override
    public void addManagerToResturant(int resturantId, Employee manager) {
        jdbcTemplate.update(
                "INSERT INTO resturant_managers (resturant_id, employee_id) VALUES (?, ?)",
                resturantId,
                manager.getEmployeeId()
        );
    }

    @Override
    public void removeManagerFromResturant(int resturantId, int managerId) {
        jdbcTemplate.update(
                "DELETE FROM resturant_managers WHERE resturant_id = ? AND employee_id = ?",
                resturantId,
                managerId
        );
    }

    @Override
    public int getResturantIdByEmployeeId(int employeeId) {
        return jdbcTemplate.queryForObject(
                "SELECT resturant_id FROM resturant_employees WHERE employee_id = ?",
                Integer.class,
                employeeId
        );
    }
    
}
