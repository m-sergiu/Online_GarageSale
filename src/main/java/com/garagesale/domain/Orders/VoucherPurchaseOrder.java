package com.garagesale.domain.Orders;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "VOUCHER_ORDER")
public class VoucherPurchaseOrder extends PurchaseOrder {


    public VoucherPurchaseOrder() {
    }

    public VoucherPurchaseOrder(VoucherPurchaseOrder.Builder builder) {
        super(builder);
    }

    public static class Builder extends PurchaseOrder.SuperBuilder {
        @Override
        public VoucherPurchaseOrder build() {
            return new VoucherPurchaseOrder(this);
        }
    }

}
