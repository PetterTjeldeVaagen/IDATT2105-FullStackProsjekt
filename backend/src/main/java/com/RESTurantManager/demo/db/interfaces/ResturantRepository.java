package com.RESTurantManager.demo.db.interfaces;

import java.util.ArrayList;

import com.RESTurantManager.demo.model.Employee;

public interface ResturantRepository {
    ArrayList<Employee> getEmployeesByResturantId(int resturantId);

    ArrayList<Employee> getManagersByResturantId(int resturantId);

    void addEmployeeToResturant(int resturantId, Employee employee);

    void removeEmployeeFromResturant(int resturantId, int employeeId);

    void addManagerToResturant(int resturantId, Employee manager);

    void removeManagerFromResturant(int resturantId, int managerId);

    int getResturantIdByEmployeeId(int employeeId);

    void createResturant(String name, int resturantId, int managerId);
}