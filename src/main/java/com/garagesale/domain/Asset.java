package com.garagesale.domain;

import com.garagesale.enums.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long asset_id;
    @Enumerated(EnumType.STRING)
    private Category category;
    private double price;
    @OneToMany
    @JoinColumn(name = "issue_id")
    private List<Issue> issues = new ArrayList<>();
    private int quantity;


    public Asset() {
    }


    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getAsset_id() {
        return this.asset_id;
    }

    public void setAsset_id(Long asset_id) {
        this.asset_id = asset_id;
    }


    public double getPrice() {
        return this.price;
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
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
