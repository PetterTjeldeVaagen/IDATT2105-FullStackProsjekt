package com.RESTurantManager.demo.model;

import java.util.ArrayList;

/**
 * Model class representing an employee. Contains fields for employee details such as name, employee ID, email, phone number, training courses, role, restaurant ID, and password.
 */
public class Employee {
    private String name;
    private int employeeId;
    private String email;
    private String phoneNumber;
    private ArrayList<Course> training;
    private String role;
    private Integer resturantId;
    private String password;

    /**
     * Default constructor for Employee.
     */
    public Employee() { }
    
    /**
     * Parameterized constructor for Employee.
     * @param name the name of the employee
     * @param employeeId the unique ID of the employee
     * @param email the email address of the employee
     * @param phoneNumber the phone number of the employee
     * @param training a list of courses completed by the employee
     * @param role the role of the employee in the restaurant
     * @param password the password for the employee's account
     * @param resturantId the ID of the restaurant the employee is associated with
     */
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

    /**
     * Adds a course to the employee's training list.
     * @param course the course to be added to the employee's training
     */
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
