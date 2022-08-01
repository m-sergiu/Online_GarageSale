package com.garagesale.domain;

import java.time.LocalDate;


public class Card {
    private String cardNumber;
    private String cardHolderName;
    private String civ;
    private int year;
    private int month;
    private double balance;

    public Card(String cardNumber, String cardHolderName, String civ, int year, int month) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.civ = civ;
        this.year = year;
        this.month = month;
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

    public Card() {
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
}
