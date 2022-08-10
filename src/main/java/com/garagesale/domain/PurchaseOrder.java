package com.garagesale.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.garagesale.domain.Cards.Card;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String customerEmail;
    @ManyToMany(mappedBy = "purchaseOrder")
    @JsonIgnore
    private List<Asset> assets = new ArrayList<>();
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Card card;
    private double purchaseBalance = 0;
    @Column(columnDefinition = "dateTime")
    private LocalDateTime dateTime;

    public PurchaseOrder() {
    }

    public static class Builder {
        private String customerName;
        private String customerEmail;
        private Card card;
        private LocalDateTime dateTime;
        public Builder(){

        }
        public PurchaseOrder build(){
            return new PurchaseOrder(this);
        }
        public Builder customerName(String customerName){
            this.customerName = customerName;
            return this;
        }
        public Builder customerEmail(String customerEmail){
            this.customerEmail = customerEmail;
            return this;
        }
        public Builder card(Card card){
            this.card = card;
            return this;
        }
        public Builder dateTime(LocalDateTime dateTime){
            this.dateTime = dateTime;
            return this;
        }
    }

    private PurchaseOrder(Builder builder) {
        this.customerName = builder.customerName;
        this.customerEmail = builder.customerEmail;
        this.card = builder.card;
        this.dateTime = builder.dateTime;
    }

    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public Card getCard() {
        return card;
    }

    public double getPurchaseBalance() {
        return purchaseBalance;
    }

    public void setPurchaseBalance(double purchaseBalance) {
        this.purchaseBalance = purchaseBalance;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
