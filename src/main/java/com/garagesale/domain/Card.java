package com.garagesale.domain;

import com.garagesale.enums.CardType;

import java.time.LocalDate;

public class Card {
    private String cardNumber;
    private String cardHolderName;
    private String CIV;
    private LocalDate expiry;
    private double balance;
    private CardType cardType;


    public Card(String cardNumber, String cardHolderName, String CIV, LocalDate expiry) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.CIV = CIV;
        this.expiry = expiry;
    }

    public Card() {
    }

    public String getCIV() {
        return CIV;
    }

    public void setCIV(String CIV) {
        this.CIV = CIV;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
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

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
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
