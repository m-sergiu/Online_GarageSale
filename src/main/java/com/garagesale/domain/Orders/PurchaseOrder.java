package com.garagesale.domain.Orders;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.garagesale.domain.Asset;
import com.garagesale.domain.Card;
import com.garagesale.enums.OrderType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "order_type", discriminatorType = DiscriminatorType.STRING)
public abstract class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OrderType orderType;
    private String customerName;
    private String customerEmail;
    @ManyToMany(mappedBy = "purchaseOrder")
    @JsonIgnore
    private List<Asset> assets = new ArrayList<>();
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Card card;
    private double voucherBalance;
    private int discountBalance;
    private double purchaseBalance = 0;
    @Column(columnDefinition = "dateTime")
    private LocalDateTime dateTime;

    public PurchaseOrder() {
    }
    public PurchaseOrder(SuperBuilder superBuilder) {
        this.customerName = superBuilder.customerName;
        this.customerEmail = superBuilder.customerEmail;
        this.card = superBuilder.card;
        this.dateTime = superBuilder.dateTime;
        this.orderType = superBuilder.orderType;
        this.voucherBalance = superBuilder.voucherBalance;
        this.discountBalance = superBuilder.discountBalance;
    }
    public abstract static class SuperBuilder {
        private String customerName;
        private String customerEmail;
        private Card card;
        private OrderType orderType;
        private LocalDateTime dateTime;
        private int discountBalance;
        private double voucherBalance;

        public abstract PurchaseOrder build();

        public SuperBuilder customerName(String customerName) {
            this.customerName = customerName;
            return this;
        }
        public SuperBuilder orderType(OrderType orderType){
            this.orderType = orderType;
            return this;
        }

        public SuperBuilder customerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
            return this;
        }

        public SuperBuilder card(Card card) {
            this.card = card;
            return this;
        }
        public SuperBuilder discountBalance(int discountBalance){
            this.discountBalance = discountBalance;
            return this;
        }
        public SuperBuilder voucherBalance(double voucherBalance){
            this.voucherBalance = voucherBalance;
            return this;
        }

        public SuperBuilder dateTime(LocalDateTime dateTime) {
            this.dateTime = dateTime;
            return this;
        }
    }
    public Long getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public List<Asset> getAssets() {
        return assets;
    }

    public void setAssets(List<Asset> assets) {
        this.assets = assets;
    }

    public Card getCard() {
        return card;
    }

    public double getPurchaseBalance() {
        return purchaseBalance;
    }
    public void setPurchaseBalance(double purchaseBalance) {
        this.purchaseBalance = purchaseBalance;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public double getVoucherBalance() {
        return voucherBalance;
    }

    public int getDiscountBalance() {
        return discountBalance;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        PurchaseOrder po = (PurchaseOrder) obj;
        return Objects.equals(customerName, po.customerName) || (customerName != null && customerName.equals(po.getCustomerName()))
                && (Objects.equals(customerEmail, po.customerEmail) || (customerEmail != null && customerEmail .equals(po.getCustomerEmail())))
                && (card == po.card || (card != null && card.equals(po.getCard())))
                && (orderType == po.orderType || (orderType != null && orderType .equals(po.getOrderType())))
                && (discountBalance == po.discountBalance)
                && (voucherBalance == po.voucherBalance);
    }


}
