package com.garagesale.factory;


import com.garagesale.domain.Card;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.OrderType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NormalOrderFactoryTest {
    Card card = new Card("4263982640269299","Sergiu","316",30,10);

    @Test
    void testCreateNormalOrder(){

        OrderDTO orderDTO = new OrderDTO(card, OrderType.NORMAL,0,0, new int[]{2},"Sergiu","Sergiu_Endava");

        NormalOrderFactory normalOrderFactory = new NormalOrderFactory();
        PurchaseOrder normalOrder = normalOrderFactory.create(orderDTO);

        assertEquals("4263982640269299",normalOrder.getCard().getCardNumber());
        assertEquals(OrderType.NORMAL,normalOrder.getOrderType());
        assertEquals("Sergiu",normalOrder.getCustomerName());
        assertEquals("Sergiu_Endava",normalOrder.getCustomerEmail());
    }
}
