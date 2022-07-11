package com.garagesale.domain;

import com.garagesale.enums.Category;

import java.util.HashMap;

public class Purchase {
    private final int id;
    private User customer;
    private HashMap<Category, Asset> purchaseCart;
    private double purchaseBalance = 0;


    public Purchase(User customer,int id) {
        this.customer = customer;
        this.purchaseCart = new HashMap();
        this.id = id;
    }


    public double getPurchaseBalance() {
        return purchaseBalance;
    }

    public void setPurchaseBalance(double purchaseBalance) {
        this.purchaseBalance = purchaseBalance;
    }

    public int getId() {
        return id;
    }

    public User getCustomer() {
        return customer;
    }

    public HashMap<Category, Asset> getPurchaseCart() {
        return purchaseCart;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

}
