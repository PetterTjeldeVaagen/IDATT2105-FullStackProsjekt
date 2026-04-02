package com.RESTurantManager.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.ResturantRepository;
import com.RESTurantManager.demo.model.Employee;

@Service
public class ResturantService {
    private final ResturantRepository resturantRepository;
    
    public ResturantService(ResturantRepository resturantRepository) {
        this.resturantRepository = resturantRepository;
    }

    public ArrayList<Employee> getEmployeesByResturantId(int resturantId) {
        return resturantRepository.getEmployeesByResturantId(resturantId);
    }

    public ArrayList<Employee> getManagersByResturantId(int resturantId) {
        return resturantRepository.getManagersByResturantId(resturantId);
    }

    public void addEmployeeToResturant(int resturantId, Employee employee) {
        resturantRepository.addEmployeeToResturant(resturantId, employee);
    }

    public void removeEmployeeFromResturant(int resturantId, int employeeId) {
        resturantRepository.removeEmployeeFromResturant(resturantId, employeeId);
    }

    public void addManagerToResturant(int resturantId, Employee manager) {
        resturantRepository.addManagerToResturant(resturantId, manager);
    }

    public void removeManagerFromResturant(int resturantId, int managerId) {
        resturantRepository.removeManagerFromResturant(resturantId, managerId);
    }

    public int getResturantIdByEmployeeId(int employeeId) {
        return resturantRepository.getResturantIdByEmployeeId(employeeId);
    }

    public void createResturant(String name, int resturantId, int managerId) {
        resturantRepository.createResturant(name, resturantId, managerId);
    }

}
