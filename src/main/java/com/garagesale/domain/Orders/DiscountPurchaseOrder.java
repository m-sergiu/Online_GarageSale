package com.garagesale.domain.Orders;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "DISCOUNT_ORDER")
public class DiscountPurchaseOrder extends PurchaseOrder {
    public DiscountPurchaseOrder() {
    }

    public DiscountPurchaseOrder(Builder builder) {
        super(builder);
    }

    public static class Builder extends PurchaseOrder.SuperBuilder {
        @Override
        public DiscountPurchaseOrder build() {
            return new DiscountPurchaseOrder(this);
        }
    }


}
