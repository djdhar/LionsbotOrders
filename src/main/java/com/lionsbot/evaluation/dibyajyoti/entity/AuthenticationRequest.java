package com.lionsbot.evaluation.dibyajyoti.entity;

import java.io.Serializable;
import java.util.UUID;

public class AuthenticationRequest implements Serializable {


    private UUID userId;
    private String password;
    private String isAdmin;

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String admin) {
        isAdmin = admin;
    }


    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //need default constructor for JSON Parsing
    public AuthenticationRequest()
    {

    }

    public AuthenticationRequest(UUID userId, String password) {
        this.setUserId(userId);
        this.setPassword(password);
    }

    public AuthenticationRequest(UUID userId, String password, String isAdmin) {
        this.setUserId(userId);
        this.setPassword(password);
        this.setIsAdmin(isAdmin);
    }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
