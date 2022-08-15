package com.garagesale.domain.Orders;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "NORMAL_ORDER")
public class NormalPurchaseOrder extends PurchaseOrder {
    public NormalPurchaseOrder() {
    }

    public NormalPurchaseOrder(Builder builder){
        super(builder);
    }
    public static class Builder extends PurchaseOrder.SuperBuilder {
        @Override
        public NormalPurchaseOrder build() {
            return new NormalPurchaseOrder(this);
        }
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
