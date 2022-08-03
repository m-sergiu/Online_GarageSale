package com.garagesale.dto;

import com.garagesale.domain.Issue;
import com.garagesale.enums.Category;

import java.util.List;

public class AssetDTO {
    private Category category;
    private double price;
    private List<Issue> issues;
    private int quantity;

    public AssetDTO() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Issue> getIssues() {
        return issues;
    }

    public void setIssues(List<Issue> issues) {
        this.issues = issues;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
