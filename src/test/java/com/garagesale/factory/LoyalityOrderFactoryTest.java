package com.garagesale.factory;

import com.garagesale.domain.Card;
import com.garagesale.domain.Orders.DiscountPurchaseOrder;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.domain.Orders.VoucherPurchaseOrder;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.OrderType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoyalityOrderFactoryTest {
    Card card = new Card("4263982640269299","Sergiu Credit","316",30,10);

    @Test
    void testCreateDiscountOrder(){

        OrderDTO orderDTO = new OrderDTO(card, OrderType.DISCOUNT,0,10, new int[]{2},"Sergiu","Sergiu");
        DiscountPurchaseOrder.Builder builder = new DiscountPurchaseOrder.Builder();
        PurchaseOrder purchaseOrder = builder.customerName(orderDTO.getCustomerName())
                .customerEmail(orderDTO.getCustomerEmail())
                .discountBalance(orderDTO.getDiscountBalance())
                .dateTime(LocalDateTime.now())
                .card(orderDTO.getCard())
                .orderType(orderDTO.getOrderType())
                .discountBalance(10)
                .build();

        AbstractOrderFactory<PurchaseOrder> abstractFactory = new LoyalityOrderFactory();
        assertEquals(purchaseOrder, abstractFactory.create(orderDTO));
    }

    @Test
    void testCreateVoucherOrder(){

        OrderDTO orderDTO = new OrderDTO(card, OrderType.VOUCHER,50,0, new int[]{2},"Sergiu","Sergiu");
        VoucherPurchaseOrder.Builder builder = new VoucherPurchaseOrder.Builder();
        PurchaseOrder purchaseOrder = builder.customerName(orderDTO.getCustomerName())
                .customerEmail(orderDTO.getCustomerEmail())
                .discountBalance(orderDTO.getDiscountBalance())
                .dateTime(LocalDateTime.now())
                .card(orderDTO.getCard())
                .orderType(orderDTO.getOrderType())
                .voucherBalance(50)
                .build();

        AbstractOrderFactory<PurchaseOrder> abstractFactory = new LoyalityOrderFactory();
        assertEquals(purchaseOrder, abstractFactory.create(orderDTO));
    }
}
