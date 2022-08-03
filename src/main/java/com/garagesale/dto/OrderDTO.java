package com.garagesale.dto;

import com.garagesale.domain.Card;

public class OrderDTO {
    private Card card;
    private int[] productID;
    private String customerName;

    public OrderDTO(Card card, int[] productID, String customerName) {
        this.card = card;
        this.productID = productID;
        this.customerName = customerName;
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

    public void setProductID(int[] productID) {
        this.productID = productID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
