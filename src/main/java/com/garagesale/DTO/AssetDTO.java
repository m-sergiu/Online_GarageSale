package com.garagesale.DTO;

import com.garagesale.enums.Category;

public class AssetDTO {
    private static int ID = 1;
    private int id;
    private Category category;
    private double price;
    private String[] issues;
    private int quantity;


    public AssetDTO(double price, String[] issues, int quantity, Category category) {
        id = IDGenerator();
        this.category = category;
        this.price = price;
        this.issues = issues;
        this.quantity = quantity;
    }

    public AssetDTO() {
    }

    public static int IDGenerator() {
        int id = ID++;
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String[] getIssues() {
        return issues;
    }

    public void setIssues(String[] issues) {
        this.issues = issues;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
