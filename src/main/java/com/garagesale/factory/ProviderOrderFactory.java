package com.garagesale.factory;

import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.enums.OrderType;

public abstract class ProviderOrderFactory {

    public static AbstractOrderFactory getOrderFactory(OrderType orderType){
        if(orderType == OrderType.NORMAL){
            return new NormalOrderFactory();
        }
        else {
            return new LoyalityOrderFactory();
        }
    }

}
