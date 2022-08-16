package com.garagesale.mapping;

import com.garagesale.domain.Card;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.DebitCard;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.OrderType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderMappingTest {

    private final Card card_credit = new CreditCard("4263982640269299","Sergiu Credit","316",30,10);
    private final Card card_debit = new DebitCard("5610591081018250","Sergiu Debit","116",30,10);

    @Test
    void testDtoToNormalOrder_credit() {
        OrderDTO orderDTO_Normal_Credit = new OrderDTO(card_credit, OrderType.NORMAL, 0, 0, new int[]{2}, "Sergiu", "Sergiu_Endava");

        PurchaseOrder purchaseOrder = OrderDTOMapping.dtoToOrder(orderDTO_Normal_Credit);

        assertEquals("Sergiu_Endava",purchaseOrder.getCustomerEmail());
        assertEquals("Sergiu",purchaseOrder.getCustomerName());
        assertEquals(OrderType.NORMAL,purchaseOrder.getOrderType());
        assertEquals("4263982640269299",purchaseOrder.getCard().getCardNumber());
        assertEquals(50000,purchaseOrder.getCard().getBalance());
    }

    @Test
    void testDtoToNormalOrder_debit(){
         OrderDTO orderDTO_Normal_Debit = new OrderDTO(card_debit, OrderType.NORMAL,0,0, new int[]{2},"Sergiu","Sergiu_Endava");

        PurchaseOrder purchaseOrder2 = OrderDTOMapping.dtoToOrder(orderDTO_Normal_Debit);

        assertEquals("Sergiu_Endava",purchaseOrder2.getCustomerEmail());
        assertEquals("Sergiu",purchaseOrder2.getCustomerName());
        assertEquals(OrderType.NORMAL,purchaseOrder2.getOrderType());
        assertEquals("5610591081018250",purchaseOrder2.getCard().getCardNumber());
        assertEquals(10,purchaseOrder2.getCard().getBalance());

    }

    @Test
    void testDtoToDiscountOrder(){
         OrderDTO orderDTO_Discount = new OrderDTO(card_credit, OrderType.DISCOUNT,0,10, new int[]{2},"Sergiu","Sergiu_Endava");

        PurchaseOrder purchaseOrder = OrderDTOMapping.dtoToOrder(orderDTO_Discount);

        assertEquals("Sergiu_Endava",purchaseOrder.getCustomerEmail());
        assertEquals("Sergiu",purchaseOrder.getCustomerName());
        assertEquals(OrderType.DISCOUNT,purchaseOrder.getOrderType());
        assertEquals(10,purchaseOrder.getDiscountBalance());
        assertEquals("4263982640269299",purchaseOrder.getCard().getCardNumber());
        assertEquals(50000,purchaseOrder.getCard().getBalance());
    }

    @Test
    void testDtoToVoucherOrder(){
        OrderDTO orderDTO_Voucher = new OrderDTO(card_credit, OrderType.VOUCHER,50,0, new int[]{2},"Sergiu","Sergiu_Endava");

        PurchaseOrder purchaseOrder = OrderDTOMapping.dtoToOrder(orderDTO_Voucher);

        assertEquals("Sergiu_Endava",purchaseOrder.getCustomerEmail());
        assertEquals("Sergiu",purchaseOrder.getCustomerName());
        assertEquals(OrderType.VOUCHER,purchaseOrder.getOrderType());
        assertEquals(50,purchaseOrder.getVoucherBalance());
        assertEquals("4263982640269299",purchaseOrder.getCard().getCardNumber());
        assertEquals(50000,purchaseOrder.getCard().getBalance());

    }

    @Test
    void testDtoToCard(){
        OrderDTO orderDTO = new OrderDTO(card_credit, OrderType.NORMAL,0,0, new int[]{2},"Sergiu","Sergiu_Endava");
        Card testCard = OrderDTOMapping.dtoToCard(orderDTO);

        assertEquals(card_credit.getCardNumber(),testCard.getCardNumber());
        assertEquals(card_credit.getCiv(),testCard.getCiv());
        assertEquals(card_credit.getBalance(),testCard.getBalance());
        assertEquals(card_credit.getMonth(),testCard.getMonth());
        assertEquals(card_credit.getYear(),testCard.getYear());
        assertEquals(card_credit.getCardHolderName(),testCard.getCardHolderName());
    }

    @Test
    void testDtoToPurchaseReceipt(){
        OrderDTO orderDTO = new OrderDTO(card_credit, OrderType.NORMAL,0,0, new int[]{2},"Sergiu","Sergiu_Endava");
        PurchaseReceipt purchaseReceipt = new PurchaseReceipt("Sergiu","Sergiu_Endava",0,"Payed by card: 4263982640269299", LocalDateTime.now());
        PurchaseReceipt testPurchaseReceipt = OrderDTOMapping.dtoToPurchaseReceipt(orderDTO);

        assertEquals(purchaseReceipt.getCustomerName(),testPurchaseReceipt.getCustomerName());
        assertEquals(purchaseReceipt.getCustomerEmail(),testPurchaseReceipt.getCustomerEmail());
        assertEquals(purchaseReceipt.getPaymentDetails(),testPurchaseReceipt.getPaymentDetails());
        assertEquals(purchaseReceipt.getDateTime().getYear(),testPurchaseReceipt.getDateTime().getYear());
        assertEquals(purchaseReceipt.getDateTime().getMonth(),testPurchaseReceipt.getDateTime().getMonth());
        assertEquals(purchaseReceipt.getDateTime().getHour(),testPurchaseReceipt.getDateTime().getHour());
        assertEquals(purchaseReceipt.getDateTime().getMinute(),testPurchaseReceipt.getDateTime().getMinute());
    }
}
