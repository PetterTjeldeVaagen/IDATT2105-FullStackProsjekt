package com.RESTurantManager.demo.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.RESTurantManager.demo.db.interfaces.ResturantRepository;
import com.RESTurantManager.demo.db.responses.ResturantResponse;
import com.RESTurantManager.demo.model.Employee;
import com.RESTurantManager.demo.model.Resturant;

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

    public ResturantResponse getResturantByEmployeeId(int employeeId) {
        Resturant resturant = resturantRepository.getResturantByEmployeeId(employeeId);
        ResturantResponse resturantResponse = new ResturantResponse(resturant.getName(), resturant.getResturantId());
        return resturantResponse;
    }

    public void createResturant(String name, int resturantId, int managerId) {
        resturantRepository.createResturant(name, resturantId, managerId);
    }

    public ResturantResponse getResturantById(int resturantId) {
        Resturant resturant = resturantRepository.getResturantById(resturantId);
        ResturantResponse resturantResponse = new ResturantResponse(resturant.getName(), resturant.getResturantId());
        return resturantResponse;
    }

}
