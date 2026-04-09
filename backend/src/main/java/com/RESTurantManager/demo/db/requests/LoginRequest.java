package com.RESTurantManager.demo.db.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    public LoginRequest() {}

    public LoginRequest(String password, String email) {
        this.password = password;
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
