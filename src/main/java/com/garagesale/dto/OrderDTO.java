package com.garagesale.dto;

import java.time.LocalDate;

public class OrderDTO {
    private String cardNumber;
    private String cardHolderName;
    private LocalDate expiry;
    private String CIV;
    private int productID;
    private String customerName;

    public OrderDTO(String cardNumber, String cardHolderName, LocalDate expiry, String CIV, int productID, String customerName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiry = expiry;
        this.CIV = CIV;
        this.productID = productID;
        this.customerName = customerName;
    }

    public String getCardNumber() {
        return cardNumber;
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

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }

    public String getCIV() {
        return CIV;
    }

    public void setCIV(String CIV) {
        this.CIV = CIV;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
