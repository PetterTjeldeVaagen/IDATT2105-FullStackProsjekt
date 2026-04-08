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
            "INSERT INTO employees (name, role, email, password, phone_number, resturant_id) VALUES (?, ?, ?, ?, ?, ?)",
            employee.getName(),
            employee.getRole(),
            employee.getEmail(),
            employee.getPassword(),
            employee.getPhoneNumber(),
            employee.getResturantId()
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

    @Override
    public Employee findByUsername(String username) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM employees WHERE name = ?",
                new BeanPropertyRowMapper<>(Employee.class),
                username
        );
    }

    @Override
    public Employee findByEmail(String email) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM employees WHERE email = ?",
                new BeanPropertyRowMapper<>(Employee.class),
                email
        );
    }

    @Override
    public Employee[] findByResturantId(int resturantId) {
        return jdbcTemplate.query(
                "SELECT * FROM employees WHERE resturant_id = ?",
                new BeanPropertyRowMapper<>(Employee.class),
                resturantId
        ).toArray(new Employee[0]);
    }
}
