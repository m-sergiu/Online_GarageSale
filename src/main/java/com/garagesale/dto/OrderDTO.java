package com.garagesale.dto;

import com.garagesale.domain.Card;
import com.garagesale.enums.OrderType;

public class OrderDTO {
    private Card card;
    private OrderType orderType;
    private double voucherBalance;
    private int discountBalance;
    private int[] productID;
    private String customerName;
    private String customerEmail;

    public OrderDTO(){
    }

    public OrderDTO(Card card, OrderType orderType, double voucherBalance, int discountBalance, int[] productID, String customerName, String customerEmail) {
        this.card = card;
        this.orderType = orderType;
        this.voucherBalance = voucherBalance;
        this.discountBalance = discountBalance;
        this.productID = productID;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public double getVoucherBalance() {
        return voucherBalance;
    }

    public void setVoucherBalance(double voucherBalance) {
        this.voucherBalance = voucherBalance;
    }

    public int getDiscountBalance() {
        return discountBalance;
    }

    public void setDiscountBalance(int discountBalance) {
        this.discountBalance = discountBalance;
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

    public OrderType getOrderType() {
        return orderType;
    }
}
