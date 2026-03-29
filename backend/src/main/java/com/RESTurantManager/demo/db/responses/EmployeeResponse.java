package com.RESTurantManager.demo.db.responses;

public class EmployeeResponse {
    private String name;
    private String email;
    private String phoneNumber;
    private int resturantId;
    private String role;
    private int employeeId;
    private String password;

    public EmployeeResponse() { }

    public EmployeeResponse(String name, String email, String phoneNumber, int resturantId, String role, int employeeId, String password) {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

