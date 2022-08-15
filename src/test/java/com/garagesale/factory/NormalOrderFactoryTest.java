package com.garagesale.factory;


import com.garagesale.domain.Card;
import com.garagesale.domain.Orders.NormalPurchaseOrder;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.OrderType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NormalOrderFactoryTest {
    Card card = new Card("4263982640269299","Sergiu Credit","316",30,10);

    @Test
    void testCreateNormalDiscountOrder(){

        OrderDTO orderDTO = new OrderDTO(card, OrderType.NORMAL,0,0, new int[]{2},"Sergiu","Sergiu");
        NormalPurchaseOrder.Builder builder = new NormalPurchaseOrder.Builder();
        PurchaseOrder purchaseOrder = builder.customerName(orderDTO.getCustomerName())
                .customerEmail(orderDTO.getCustomerEmail())
                .discountBalance(orderDTO.getDiscountBalance())
                .dateTime(LocalDateTime.now())
                .card(orderDTO.getCard())
                .orderType(orderDTO.getOrderType())
                .build();

        AbstractOrderFactory<PurchaseOrder> abstractFactory = new NormalOrderFactory();

        assertEquals(purchaseOrder, abstractFactory.create(orderDTO));

    }
}
