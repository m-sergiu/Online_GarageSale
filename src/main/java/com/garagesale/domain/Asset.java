package com.garagesale.domain;

import com.garagesale.enums.Category;

public class Asset {
    private int id;
    private String assetName;
    private Category category;
    private double price;
    private String[] issues;
    private int quantity;

    public Asset(Category category, int id, String assetName, double price, String[] issues, int quantity) {
        this.category = category;
        this.id = id;
        this.assetName = assetName;
        this.price = price;
        this.issues = issues;
        this.quantity = quantity;
    }

    public Asset() {
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
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
