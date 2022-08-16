package com.garagesale.factory;

import com.garagesale.domain.Card;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.OrderType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoyalityOrderFactoryTest {
    Card card = new Card("4263982640269299","Sergiu Credit","316",30,10);

    @Test
    void testCreateDiscountOrder(){

        OrderDTO orderDTO = new OrderDTO(card, OrderType.DISCOUNT,0,10, new int[]{2},"Sergiu","Sergiu_Endava");

        LoyalityOrderFactory discountOrderFactory = new LoyalityOrderFactory();
        PurchaseOrder discountOrder = discountOrderFactory.create(orderDTO);

        assertEquals("4263982640269299",discountOrder.getCard().getCardNumber());
        assertEquals(OrderType.DISCOUNT,discountOrder.getOrderType());
        assertEquals("Sergiu",discountOrder.getCustomerName());
        assertEquals("Sergiu_Endava",discountOrder.getCustomerEmail());
        assertEquals(10,discountOrder.getDiscountBalance());
    }

    @Test
    void testCreateVoucherOrder(){

        OrderDTO orderDTO = new OrderDTO(card, OrderType.VOUCHER,50,0, new int[]{2},"Sergiu","Sergiu_Endava");

        LoyalityOrderFactory voucherOrderFactory = new LoyalityOrderFactory();
        PurchaseOrder voucherOrder = voucherOrderFactory.create(orderDTO);

        assertEquals("4263982640269299",voucherOrder.getCard().getCardNumber());
        assertEquals(OrderType.VOUCHER,voucherOrder.getOrderType());
        assertEquals("Sergiu",voucherOrder.getCustomerName());
        assertEquals("Sergiu_Endava",voucherOrder.getCustomerEmail());
        assertEquals(50,voucherOrder.getVoucherBalance());
    }
}
