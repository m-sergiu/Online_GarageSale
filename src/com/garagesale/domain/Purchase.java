package com.garagesale.domain;

import com.garagesale.enums.Category;

import java.util.HashMap;

public class Purchase {
    private final int ID;
    private static int currentID = 1;
    private User customer;
    private HashMap<Category, Asset> purchaseCart;
    private double purchaseBalance = 0;


    public Purchase(User customer) {
        this.customer = customer;
        this.purchaseCart = new HashMap();
        this.ID = IDGenerator();
    }

    static int IDGenerator() {
        int ID = currentID++;
        return ID;
    }

    public double getPurchaseBalance() {
        return purchaseBalance;
    }

    public void setPurchaseBalance(double purchaseBalance) {
        this.purchaseBalance = purchaseBalance;
    }

    public int getID() {
        return ID;
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
