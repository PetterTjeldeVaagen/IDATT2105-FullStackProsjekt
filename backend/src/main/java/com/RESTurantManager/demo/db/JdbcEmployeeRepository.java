package com.RESTurantManager.demo.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.RESTurantManager.demo.db.interfaces.EmployeeRepository;
import com.RESTurantManager.demo.model.Employee;

@Repository
public class JdbcEmployeeRepository implements EmployeeRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Employee employee) {
        jdbcTemplate.update(
                "INSERT INTO employees (employee_id, name, role, email, phone_number) VALUES (?, ?, ?, ?, ?)",
                employee.getEmployeeId(),
                employee.getName(),
                employee.getRole(), 
                employee.getEmail(),
                employee.getPhoneNumber()
        );
    }

    @Override
    public Employee findById(int id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM employees WHERE employee_id = ?",
                new BeanPropertyRowMapper<>(Employee.class),
                id
        );
    }

    @Override
    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM employees WHERE employee_id = ?", id);
    }
}
