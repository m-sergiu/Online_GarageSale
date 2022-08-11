package com.garagesale.factory;


import com.garagesale.domain.Orders.DiscountPurchaseOrder;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.domain.Orders.VoucherPurchaseOrder;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.OrderType;
import com.garagesale.mapping.OrderDTOMapping;

import java.time.LocalDateTime;

public class LoyalityOrderFactory implements AbstractOrderFactory {

    @Override
    public PurchaseOrder createOrder(OrderType orderType, OrderDTO orderDTO) {
        if(orderType == OrderType.DISCOUNT) {
            DiscountPurchaseOrder.Builder builder = new DiscountPurchaseOrder.Builder();
            builder.customerName(orderDTO.getCustomerName())
                    .customerEmail(orderDTO.getCustomerEmail())
                    .card(OrderDTOMapping.dtoToCreditCard(orderDTO))
                    .discountBalance(orderDTO.getDiscountBalance())
                    .dateTime(LocalDateTime.now())
                    .orderType(orderDTO.getOrderType())
                    .build();
            DiscountPurchaseOrder order = builder.build();
            return order;
        }
        else {
            VoucherPurchaseOrder.Builder builder = new VoucherPurchaseOrder.Builder();
            builder.customerName(orderDTO.getCustomerName())
                    .customerEmail(orderDTO.getCustomerEmail())
                    .card(OrderDTOMapping.dtoToCreditCard(orderDTO))
                    .voucherBalance(orderDTO.getVoucherBalance())
                    .dateTime(LocalDateTime.now())
                    .orderType(orderDTO.getOrderType())
                    .build();
            VoucherPurchaseOrder order = builder.build();
            return order;
        }
    }
}
