package com.garagesale.factory;

import com.garagesale.domain.Card;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.OrderType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProviderOrderFactoryTest {



    @Test
    void testGetOrderFactory(){
        Card card = new CreditCard("4263982640269299","Sergiu","316",30,10);
        OrderDTO orderDTO_Normal = new OrderDTO(card, OrderType.NORMAL,0,0, new int[]{1},"Sergiu","Sergiu");
        OrderDTO orderDTO_Discount = new OrderDTO(card, OrderType.DISCOUNT,0,0, new int[]{1},"Sergiu","Sergiu");
        OrderDTO orderDTO_Voucher = new OrderDTO(card, OrderType.VOUCHER,0,0, new int[]{1},"Sergiu","Sergiu");

        AbstractOrderFactory<PurchaseOrder> abstractFactory_normal = new NormalOrderFactory();
        AbstractOrderFactory<PurchaseOrder> abstractFactory_discount = new LoyalityOrderFactory();
        AbstractOrderFactory<PurchaseOrder> abstractFactory_voucher = new LoyalityOrderFactory();

        assertEquals(abstractFactory_normal.getClass(),ProviderOrderFactory.getInstance().getOrderFactory(orderDTO_Normal.getOrderType()).getClass());
        assertEquals(abstractFactory_discount.getClass(),ProviderOrderFactory.getInstance().getOrderFactory(orderDTO_Discount.getOrderType()).getClass());
        assertEquals(abstractFactory_voucher.getClass(),ProviderOrderFactory.getInstance().getOrderFactory(orderDTO_Voucher.getOrderType()).getClass());
    }
}
