package com.garagesale.domain;

import com.garagesale.enums.Category;

import java.util.HashMap;


public class Purchase {
    private int id;
    private User customer;
    private HashMap<Category, Asset> purchaseCart;
    private CreditCard creditCard;
    private double purchaseBalance = 0;

    public double getPurchaseBalance() {
        return purchaseBalance;
    }

    public void setPurchaseBalance(double purchaseBalance) {
        this.purchaseBalance = purchaseBalance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public HashMap<Category, Asset> getPurchaseCart() {
        return purchaseCart;
    }

    public void setPurchaseCart(HashMap<Category, Asset> purchaseCart) {
        this.purchaseCart = purchaseCart;
    }

}
