package com.betting.backend.auth;

public class AuthResponse {
    /**
     * This class is a Data Transfer object that wraps a JWT token in a structured Json response
     */
    private String token;//this is what will be returned to the client

    public AuthResponse(String token) {

        /**
         *This initalizes the AuthReponse class
         takes in a token as a paramater so it can be used by the getters and setters
         */
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
