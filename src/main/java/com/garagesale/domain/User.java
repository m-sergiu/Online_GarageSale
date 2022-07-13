package com.garagesale.domain;

import com.garagesale.enums.Category;
import com.garagesale.exceptions.ProductAlreadyInCartException;
import org.springframework.stereotype.Component;

import java.util.HashMap;


public class User {
    private String username;
    private String email;




    public User(String username, String email) {
        this.username = username;
        this.email = email;
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


}
