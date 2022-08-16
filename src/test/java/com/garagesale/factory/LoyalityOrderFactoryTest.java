package com.garagesale.factory;

import com.garagesale.domain.Card;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.OrderType;
import com.garagesale.mapping.OrderDTOMapping;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoyalityOrderFactoryTest {
    Card card = new Card("4263982640269299","Sergiu Credit","316",30,10);

    @Test
    void testCreateDiscountOrder(){

        OrderDTO orderDTO = new OrderDTO(card, OrderType.DISCOUNT,0,10, new int[]{2},"Sergiu","Sergiu");
        PurchaseOrder purchaseOrder = OrderDTOMapping.dtoToOrder(orderDTO);

        AbstractOrderFactory<PurchaseOrder> abstractFactory = new LoyalityOrderFactory();
        assertEquals(purchaseOrder, abstractFactory.create(orderDTO));
    }

    @Test
    void testCreateVoucherOrder(){

        OrderDTO orderDTO = new OrderDTO(card, OrderType.VOUCHER,50,0, new int[]{2},"Sergiu","Sergiu");
        PurchaseOrder purchaseOrder = OrderDTOMapping.dtoToOrder(orderDTO);

        AbstractOrderFactory<PurchaseOrder> abstractFactory = new LoyalityOrderFactory();
        assertEquals(purchaseOrder, abstractFactory.create(orderDTO));
    }
}
