package com.garagesale.domain;

public class CreditCard {
    private final String cardNumber;
    private final String cardHolderName;
    private double balance;

    public CreditCard(String cardNumber, double balance, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.cardHolderName = cardHolderName;
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
