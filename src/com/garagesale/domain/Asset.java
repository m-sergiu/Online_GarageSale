package com.garagesale.domain;

public class Asset {
    private String assetName;
    private double price;
    private String[] issues;
    private int quantity;
    private final Category category;
    private int ID;
    private static int currentID = 1;

    static int IDGenerator() {
        int ID = currentID++;
        return ID;
    }
    public Asset(String assetName, double price, String[] issues, int quantity, Category category) {
        this.assetName = assetName;
        this.price = price;
        this.issues = issues;
        this.quantity = quantity;
        this.category = category;
        this.ID = IDGenerator();
    }



    public Category getCategory() {
        return this.category;
    }

    public int getID() {
        return this.ID;
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
