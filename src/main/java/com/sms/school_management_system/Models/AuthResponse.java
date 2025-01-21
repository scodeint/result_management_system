package com.sms.school_management_system.Models;

public class AuthResponse {
    private String token;
    private String message;

    // Default constructor
    public AuthResponse() {
    }

    // Constructor for token with default message
    public AuthResponse(String token) {
        this.token = token;
        this.message = "Login successful";  // Default message
    }

    // Constructor for both token and message
    public AuthResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
