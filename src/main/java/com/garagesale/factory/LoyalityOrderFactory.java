package com.garagesale.factory;


import com.garagesale.domain.Orders.DiscountPurchaseOrder;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.domain.Orders.VoucherPurchaseOrder;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.OrderType;
import com.garagesale.mapping.OrderDTOMapping;

import java.time.LocalDateTime;

public class LoyalityOrderFactory implements AbstractOrderFactory<PurchaseOrder> {

    @Override
    public PurchaseOrder create(OrderDTO orderDTO) {
        if(orderDTO.getOrderType() == OrderType.DISCOUNT) {
            DiscountPurchaseOrder.Builder builder = new DiscountPurchaseOrder.Builder();
            return   builder.customerName(orderDTO.getCustomerName())
                    .customerEmail(orderDTO.getCustomerEmail())
                    .card(OrderDTOMapping.dtoToCard(orderDTO))
                    .discountBalance(orderDTO.getDiscountBalance())
                    .dateTime(LocalDateTime.now())
                    .orderType(orderDTO.getOrderType())
                    .build();
        }
        else {
            VoucherPurchaseOrder.Builder builder = new VoucherPurchaseOrder.Builder();
          return  builder.customerName(orderDTO.getCustomerName())
                    .customerEmail(orderDTO.getCustomerEmail())
                    .card(OrderDTOMapping.dtoToCard(orderDTO))
                    .voucherBalance(orderDTO.getVoucherBalance())
                    .dateTime(LocalDateTime.now())
                    .orderType(orderDTO.getOrderType())
                    .build();
        }
    }
}
