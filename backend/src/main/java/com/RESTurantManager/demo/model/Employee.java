package com.RESTurantManager.demo.model;

import java.util.ArrayList;

public class Employee {
    private String name;
    private int employeeId;
    private String email;
    private String phoneNumber;
    private ArrayList<Course> training;
    private String role;
    private Integer resturantId;
    private String password;

    public Employee() { }
    
    public Employee(String name, int employeeId, String email, String phoneNumber, ArrayList<Course> training, String role, String password, Integer resturantId) {
        this.name = name;
        this.employeeId = employeeId;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.training = training;
        this.role = role;
        this.password = password;
        this.resturantId = resturantId;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getResturantId() {
        return resturantId;
    }

    public void setResturantId(Integer resturantId) {
        this.resturantId = resturantId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
