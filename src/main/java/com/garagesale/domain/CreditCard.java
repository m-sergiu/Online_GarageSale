package com.garagesale.domain;

import java.util.Date;

public class CreditCard {
    private final String cardNumber;
    private final String cardHolderName;
    private final String CIV;
    private Date expiry;

    private double balance;

    public CreditCard(String cardNumber, double balance, String cardHolderName, String CIV, Date expiry) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.cardHolderName = cardHolderName;
        this.CIV = CIV;
        this.expiry = expiry;
    }

    public String getCIV() {
        return CIV;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getCardNumber() {
        return cardNumber;
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
}
