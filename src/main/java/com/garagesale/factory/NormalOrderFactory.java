package com.garagesale.factory;

import com.garagesale.domain.Orders.NormalPurchaseOrder;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.dto.OrderDTO;
import com.garagesale.mapping.OrderDTOMapping;

import java.time.LocalDateTime;

public class NormalOrderFactory implements AbstractOrderFactory <PurchaseOrder> {

    public PurchaseOrder create(OrderDTO orderDTO) {
        NormalPurchaseOrder.Builder builder = new NormalPurchaseOrder.Builder();
        return builder.customerName(orderDTO.getCustomerName())
                .customerEmail(orderDTO.getCustomerEmail())
                .card(OrderDTOMapping.dtoToCreditCard(orderDTO))
                .discountBalance(orderDTO.getDiscountBalance())
                .dateTime(LocalDateTime.now())
                .orderType(orderDTO.getOrderType())
                .build();
    }
}
