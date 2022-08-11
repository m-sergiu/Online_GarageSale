package com.garagesale.factory;

import com.garagesale.enums.OrderType;

public abstract class ProviderOrderFactory {

    public static AbstractOrderFactory getOrderFactory(OrderType orderType){
        if(orderType == OrderType.DISCOUNT || orderType==OrderType.VOUCHER){
            return new LoyalityOrderFactory();
        }
        else {
            return new NormalOrderFactory();
        }
    }

}
