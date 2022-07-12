package com.garagesale.domain;

import com.garagesale.enums.Category;

public class Asset {
    private final Category category;
    private final int id;
    private String assetName;
    private double price;
    private String[] issues;
    private int quantity;

    public Asset(String assetName, double price, String[] issues, int quantity, Category category, int id) {
        this.assetName = assetName;
        this.price = price;
        this.issues = issues;
        this.quantity = quantity;
        this.category = category;
        this.id = id;
    }

    public Category getCategory() {
        return this.category;
    }

    public int getId() {
        return this.id;
    }

    public String getAssetName() {
        return this.assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getIssues() {
        return this.issues;
    }

    public void setIssues(String[] issues) {
        this.issues = issues;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
