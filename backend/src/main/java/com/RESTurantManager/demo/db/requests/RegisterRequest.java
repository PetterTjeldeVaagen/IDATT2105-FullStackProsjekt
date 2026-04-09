package com.RESTurantManager.demo.db.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request class for registration. Contains fields for username, password and email.
 */
public class RegisterRequest {
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    @Email
    @NotBlank
    private String email;

    /**
     * Default constructor for RegisterRequest.
     */    
    public RegisterRequest() {}

    /**
     * Parameterized constructor for RegisterRequest.
     * @param username the username of the user
     * @param password the password of the user
     * @param email the email of the user
     */
    public RegisterRequest(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
