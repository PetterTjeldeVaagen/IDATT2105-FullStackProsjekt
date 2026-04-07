package com.RESTurantManager.demo.db.responses;

public class LoginResponse {
    private String response;
    private String token;
    private int employeeId;
    private String email;
    private String username;

    public LoginResponse() {}

    public LoginResponse(String response) {
        this.response = response;
    }

    public LoginResponse(String response,  String token, int employeeId, String email, String username) {
        this.response = response;
        this.token = token;
        this.employeeId = employeeId;
        this.email = email;
        this.username = username;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
