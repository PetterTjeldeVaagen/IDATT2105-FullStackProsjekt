package com.RESTurantManager.demo.model;

import java.util.ArrayList;

public class Employee {
    private String name;
    private int employeeId;
    private String email;
    private String phoneNumber;
    private ArrayList<Course> training;
    
    public Employee(String name, int employeeId, String email, String phoneNumber, ArrayList<Course> training) {
        this.name = name;
        this.employeeId = employeeId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.training = training;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public ArrayList<Course> getTraining() {
        return training;
    }

    public void setTraining(ArrayList<Course> training) {
        this.training = training;
    }

    public void addCourse(Course course) {
        this.training.add(course);
    }
}
