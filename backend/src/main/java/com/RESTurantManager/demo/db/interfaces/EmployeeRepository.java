package com.RESTurantManager.demo.db.interfaces;

import com.RESTurantManager.demo.model.Employee;

public interface EmployeeRepository {
    void save(Employee employee);

    Employee findById(int id);

    void deleteById(int id);
}
