package com.RESTurantManager.demo.db.requests;

/**
 * Request class for creating or updating an employee. Contains fields for employee details such as 
 * name, email, phone number, restaurant ID, role, employee ID and password.
 */
public class EmployeeRequest {
    private String name;
    private String email;
    private String phoneNumber;
    private int resturantId;
    private String role;
    private int employeeId;
    private String password;

    /**
     * Default constructor for EmployeeRequest.
     */
    public EmployeeRequest() { }

    /**
     * Parameterized constructor for EmployeeRequest.
     * @param name the name of the employee
     * @param email the email of the employee
     * @param phoneNumber the phone number of the employee
     * @param resturantId the ID of the restaurant the employee belongs to
     * @param role the role of the employee
     * @param employeeId the ID of the employee
     * @param password the password of the employee
     */
    public EmployeeRequest(String name, String email, String phoneNumber, int resturantId, String role, int employeeId, String password) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.resturantId = resturantId;
        this.role = role;
        this.employeeId = employeeId;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getResturantId() {
        return resturantId;
    }

    public void setResturantId(int resturantId) {
        this.resturantId = resturantId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
