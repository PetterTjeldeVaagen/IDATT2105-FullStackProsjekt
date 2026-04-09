package com.RESTurantManager.demo.db.responses;

/**
 * Response class for login. Contains fields for response message, authentication token, employee ID, email and username.
 */
public class LoginResponse {
    private String response;
    private String token;
    private int employeeId;
    private String email;
    private String username;

    /**
     * Default constructor for LoginResponse.
     */
    public LoginResponse() {}

    /**
     * Parameterized constructor for LoginResponse.
     * @param response a response message indicating the result of a login operation
     */
    public LoginResponse(String response) {
        this.response = response;
    }

    /**
     * Parameterized constructor for LoginResponse.
     * @param response a response message indicating the result of a login operation
     * @param token the authentication token generated upon successful login
     * @param employeeId the ID of the employee who logged in
     * @param email the email of the employee who logged in
     * @param username the username of the employee who logged in
     */
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
