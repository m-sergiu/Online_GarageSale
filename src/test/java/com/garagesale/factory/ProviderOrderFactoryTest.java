package com.garagesale.factory;

import com.garagesale.enums.OrderType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProviderOrderFactoryTest {



    @Test
    void testGetOrderFactory() {

        assertTrue(ProviderOrderFactory.getInstance().getOrderFactory(OrderType.NORMAL) instanceof NormalOrderFactory);
        assertTrue(ProviderOrderFactory.getInstance().getOrderFactory(OrderType.DISCOUNT) instanceof LoyalityOrderFactory);
        assertTrue(ProviderOrderFactory.getInstance().getOrderFactory(OrderType.VOUCHER) instanceof LoyalityOrderFactory);
    }
}
