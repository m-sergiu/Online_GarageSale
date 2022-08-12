package com.garagesale.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.enums.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "asset")
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Category category;
    private double price;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asset", fetch = FetchType.LAZY)
    private List<Issue> issues = new ArrayList<>();
    private int quantity;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "asset_purchaseOrder", joinColumns = @JoinColumn(name = "asset_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "purchaseOrder_id", referencedColumnName = "id"))
    @JsonIgnore
    private List<PurchaseOrder> purchaseOrder = new ArrayList<>();


    public Asset() {
    }

    public Asset(Long id, Category category, double price, List<Issue> issues, int quantity) {
        this.id = id;
        this.category = category;
        this.price = price;
        this.issues = issues;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public List<PurchaseOrder> getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(List<PurchaseOrder> purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

}
