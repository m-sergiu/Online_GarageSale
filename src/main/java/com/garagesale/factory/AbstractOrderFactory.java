package com.garagesale.factory;

import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.OrderType;

public interface AbstractOrderFactory {
    public PurchaseOrder createOrder(OrderType orderType, OrderDTO orderDTO);
}
