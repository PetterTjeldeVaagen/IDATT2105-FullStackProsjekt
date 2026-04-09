package com.RESTurantManager.demo.db.responses;

/**
 * Response class for employee-related operations. Contains fields for employee details such as name, email, phone number, restaurant ID, role and employee ID.
 */
public class EmployeeResponse {
    private String name;
    private String email;
    private String phoneNumber;
    private int resturantId;
    private String role;
    private int employeeId;

    /**
     * Default constructor for EmployeeResponse.
     */
    public EmployeeResponse() { }

    /**
     * Parameterized constructor for EmployeeResponse.
     * @param name the name of the employee
     * @param email the email of the employee
     * @param phoneNumber the phone number of the employee
     * @param resturantId the ID of the restaurant the employee belongs to
     * @param role the role of the employee
     * @param employeeId the ID of the employee
     */
    public EmployeeResponse(String name, String email, String phoneNumber, int resturantId, String role, int employeeId) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.resturantId = resturantId;
        this.role = role;
        this.employeeId = employeeId;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}

