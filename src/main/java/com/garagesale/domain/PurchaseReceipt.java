package com.garagesale.domain;

import com.garagesale.enums.Category;

import java.time.LocalDateTime;
import java.util.HashMap;

public class PurchaseReceipt {
    private String customerName;
    private String customerEmail;
    private HashMap<Category, Double> assets;
    private double totalAmount;
    private String paymentDetails;
    private LocalDateTime dateTime;


    public PurchaseReceipt() {
    }

    public PurchaseReceipt(String customerName, String customerEmail, double totalAmount, String paymentDetails, LocalDateTime dateTime) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.totalAmount = totalAmount;
        this.paymentDetails = paymentDetails;
        this.dateTime = dateTime;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }


    public HashMap<Category, Double> getAssets() {
        return assets;
    }

    public void setAssets(HashMap<Category, Double> assets) {
        this.assets = assets;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
