package com.garagesale.factory;

import com.garagesale.enums.OrderType;

public class ProviderOrderFactory {

    private static volatile ProviderOrderFactory instance = null;

    //SINGLETON pattern class
    private ProviderOrderFactory(){
        if(instance != null){
            throw new RuntimeException("Already exists an instane. Use getInstance() method");
        }
    }

    public static ProviderOrderFactory getInstance(){
        if(instance == null){
            synchronized (ProviderOrderFactory.class){
                if(instance==null){
                    instance = new ProviderOrderFactory();
                }
            }

        }
        return instance;
    }
    public static AbstractOrderFactory getOrderFactory(OrderType orderType){
        if(orderType == OrderType.DISCOUNT || orderType==OrderType.VOUCHER){
            return new LoyalityOrderFactory();
        }
        else {
            return new NormalOrderFactory();
        }
    }

}
