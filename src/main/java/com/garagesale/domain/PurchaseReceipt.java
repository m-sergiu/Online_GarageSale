package com.garagesale.domain;

import java.util.List;


public class PurchaseReceipt {
    private String customerName;
    private String customerEmail;
    private Card card;
    private int ID;
    private List<Asset> assetList;
    private double totalAmount;
    private String paymentDetails;

    public PurchaseReceipt() {
    }

    public PurchaseReceipt(String customerName, String customerEmail, int ID, List<Asset> assetList, double totalAmount, String paymentDetails) {
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.ID = ID;
        this.assetList = assetList;
        this.totalAmount = totalAmount;
        this.paymentDetails = paymentDetails;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public List<Asset> getAssetList() {
        return assetList;
    }

    public void setAssetList(List<Asset> assetList) {
        this.assetList = assetList;
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

}
