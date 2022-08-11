package com.garagesale.factory;

import com.garagesale.domain.Orders.NormalPurchaseOrder;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.OrderType;
import com.garagesale.mapping.OrderDTOMapping;

import java.time.LocalDateTime;

public class NormalOrderFactory implements AbstractOrderFactory {

    @Override
    public PurchaseOrder createOrder(OrderType orderType, OrderDTO orderDTO) {
        NormalPurchaseOrder.Builder builder = new NormalPurchaseOrder.Builder();
        builder.customerName(orderDTO.getCustomerName())
                .customerEmail(orderDTO.getCustomerEmail())
                .card(OrderDTOMapping.dtoToCreditCard(orderDTO))
                .discountBalance(orderDTO.getDiscountBalance())
                .dateTime(LocalDateTime.now())
                .orderType(orderDTO.getOrderType())
                .build();
        NormalPurchaseOrder order = builder.build();
        return order;
    }
}
