package com.garagesale.dto;

import com.garagesale.domain.Cards.Card;

public class OrderDTO {
    private Card card;
    private int[] productID;
    private String customerName;
    private String customerEmail;

    public OrderDTO(){
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }


    public int[] getProductID() {
        return productID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
