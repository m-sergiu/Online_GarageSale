package com.garagesale.domain;

import com.garagesale.enums.Category;

import java.util.HashMap;


public class Order {
    private int id;
    private User customer;
    private HashMap<Category, Asset> purchaseCart;
    private Card card;
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

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
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

    public void addAssetToOrderCart(Asset asset) {
        purchaseCart.put(asset.getCategory(), asset);
    }
}
