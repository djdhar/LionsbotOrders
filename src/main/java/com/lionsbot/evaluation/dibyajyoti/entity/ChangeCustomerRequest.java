package com.lionsbot.evaluation.dibyajyoti.entity;

import java.io.Serializable;

public class ChangeCustomerRequest implements Serializable {

    private String password;

    public ChangeCustomerRequest(String password) {
        this.password = password;
    }

    public ChangeCustomerRequest() {

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
