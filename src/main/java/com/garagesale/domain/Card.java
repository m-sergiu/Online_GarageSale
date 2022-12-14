package com.garagesale.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.garagesale.domain.Orders.PurchaseOrder;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "card_type", discriminatorType = DiscriminatorType.STRING)
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @OneToOne
    private PurchaseOrder purchaseOrder;
    private String cardNumber;
    private String cardHolderName;
    private String civ;
    private int year;
    private int month;
    private double balance;

    public Card() {
    }

    public Card(String cardNumber, String cardHolderName, String civ, int year, int month) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.civ = civ;
        this.year = year;
        this.month = month;
    }

    public Long getId() {
        return id;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public String getCiv() {
        return civ;
    }

    public void setCiv(String civ) {
        this.civ = civ;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

}
