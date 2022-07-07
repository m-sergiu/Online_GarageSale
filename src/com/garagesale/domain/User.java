package com.garagesale.domain;

import com.garagesale.enums.Category;
import com.garagesale.exceptions.ProductAlreadyInCartException;

import java.util.HashMap;

public class User {
    private String username;
    private String email;
    private CreditCard creditCard;



    public User(String username, String email, CreditCard creditCard) {
        this.username = username;
        this.email = email;
        this.creditCard = creditCard;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }
}
