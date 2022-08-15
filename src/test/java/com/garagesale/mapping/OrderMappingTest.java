package com.garagesale.mapping;

import com.garagesale.domain.Card;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.DebitCard;
import com.garagesale.domain.Orders.DiscountPurchaseOrder;
import com.garagesale.domain.Orders.NormalPurchaseOrder;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.domain.Orders.VoucherPurchaseOrder;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.OrderType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderMappingTest {

    Card card_credit = new CreditCard("4263982640269299","Sergiu Credit","316",30,10);
    Card card_debit = new DebitCard("5610591081018250","Sergiu Debit","116",30,10);

    @Test
    void testDtoToNormalOrder(){
          OrderDTO orderDTO_Normal_Credit = new OrderDTO(card_credit, OrderType.NORMAL,0,0, new int[]{2},"Sergiu","Sergiu");
          OrderDTO orderDTO_Normal_Debit = new OrderDTO(card_debit, OrderType.NORMAL,0,0, new int[]{2},"Sergiu","Sergiu");

        //CREDIT CARD
        NormalPurchaseOrder.Builder builder_credit = new NormalPurchaseOrder.Builder();
        PurchaseOrder purchaseOrder = builder_credit.customerName(orderDTO_Normal_Credit.getCustomerName())
                .customerEmail(orderDTO_Normal_Credit.getCustomerEmail())
                .discountBalance(orderDTO_Normal_Credit.getDiscountBalance())
                .dateTime(LocalDateTime.now())
                .card(orderDTO_Normal_Credit.getCard())
                .orderType(orderDTO_Normal_Credit.getOrderType())
                .build();

        assertEquals(purchaseOrder.getCustomerEmail(), OrderDTOMapping.dtoToOrder(orderDTO_Normal_Credit).getCustomerEmail());
        assertEquals(purchaseOrder.getCustomerName(), OrderDTOMapping.dtoToOrder(orderDTO_Normal_Credit).getCustomerName());
        assertEquals(purchaseOrder.getOrderType(), OrderDTOMapping.dtoToOrder(orderDTO_Normal_Credit).getOrderType());
        assertEquals(purchaseOrder.getCard().getCardNumber(), OrderDTOMapping.dtoToOrder(orderDTO_Normal_Credit).getCard().getCardNumber());
        assertEquals(purchaseOrder.getCard().getBalance(), OrderDTOMapping.dtoToOrder(orderDTO_Normal_Credit).getCard().getBalance());

        //DEBIT CARD
        NormalPurchaseOrder.Builder builder_debit = new NormalPurchaseOrder.Builder();
        PurchaseOrder purchaseOrder2 = builder_debit.customerName(orderDTO_Normal_Debit.getCustomerName())
                .customerEmail(orderDTO_Normal_Debit.getCustomerEmail())
                .discountBalance(orderDTO_Normal_Debit.getDiscountBalance())
                .dateTime(LocalDateTime.now())
                .card(orderDTO_Normal_Debit.getCard())
                .orderType(orderDTO_Normal_Debit.getOrderType())
                .build();

        assertEquals(purchaseOrder2.getCustomerEmail(), OrderDTOMapping.dtoToOrder(orderDTO_Normal_Debit).getCustomerEmail());
        assertEquals(purchaseOrder2.getCustomerName(), OrderDTOMapping.dtoToOrder(orderDTO_Normal_Debit).getCustomerName());
        assertEquals(purchaseOrder2.getOrderType(), OrderDTOMapping.dtoToOrder(orderDTO_Normal_Debit).getOrderType());
        assertEquals(purchaseOrder2.getCard().getCardNumber(), OrderDTOMapping.dtoToOrder(orderDTO_Normal_Debit).getCard().getCardNumber());
        assertEquals(purchaseOrder2.getCard().getBalance(), OrderDTOMapping.dtoToOrder(orderDTO_Normal_Debit).getCard().getBalance());

    }

    @Test
    void testDtoToDiscountOrder(){
         OrderDTO orderDTO_Discount = new OrderDTO(card_credit, OrderType.DISCOUNT,0,10, new int[]{2},"Sergiu","Sergiu");

        //NORMAL ORDER
        DiscountPurchaseOrder.Builder builder = new DiscountPurchaseOrder.Builder();
        PurchaseOrder purchaseOrder = builder.customerName(orderDTO_Discount.getCustomerName())
                .customerEmail(orderDTO_Discount.getCustomerEmail())
                .discountBalance(orderDTO_Discount.getDiscountBalance())
                .dateTime(LocalDateTime.now())
                .card(orderDTO_Discount.getCard())
                .orderType(orderDTO_Discount.getOrderType())
                .discountBalance(10)
                .build();

        assertEquals(purchaseOrder.getCustomerEmail(), OrderDTOMapping.dtoToOrder(orderDTO_Discount).getCustomerEmail());
        assertEquals(purchaseOrder.getCustomerName(), OrderDTOMapping.dtoToOrder(orderDTO_Discount).getCustomerName());
        assertEquals(purchaseOrder.getOrderType(), OrderDTOMapping.dtoToOrder(orderDTO_Discount).getOrderType());
        assertEquals(purchaseOrder.getDiscountBalance(), OrderDTOMapping.dtoToOrder(orderDTO_Discount).getDiscountBalance());
        assertEquals(purchaseOrder.getCard().getCardNumber(), OrderDTOMapping.dtoToOrder(orderDTO_Discount).getCard().getCardNumber());

    }

    @Test
    void testDtoToVoucherOrder(){
        OrderDTO orderDTO_Voucher = new OrderDTO(card_credit, OrderType.VOUCHER,50,0, new int[]{2},"Sergiu","Sergiu");

        //NORMAL ORDER
        VoucherPurchaseOrder.Builder builder = new VoucherPurchaseOrder.Builder();
        PurchaseOrder purchaseOrder = builder.customerName(orderDTO_Voucher.getCustomerName())
                .customerEmail(orderDTO_Voucher.getCustomerEmail())
                .discountBalance(orderDTO_Voucher.getDiscountBalance())
                .dateTime(LocalDateTime.now())
                .card(orderDTO_Voucher.getCard())
                .orderType(orderDTO_Voucher.getOrderType())
                .voucherBalance(50)
                .build();

        assertEquals(purchaseOrder.getCustomerEmail(), OrderDTOMapping.dtoToOrder(orderDTO_Voucher).getCustomerEmail());
        assertEquals(purchaseOrder.getCustomerName(), OrderDTOMapping.dtoToOrder(orderDTO_Voucher).getCustomerName());
        assertEquals(purchaseOrder.getOrderType(), OrderDTOMapping.dtoToOrder(orderDTO_Voucher).getOrderType());
        assertEquals(purchaseOrder.getVoucherBalance(), OrderDTOMapping.dtoToOrder(orderDTO_Voucher).getVoucherBalance());
        assertEquals(purchaseOrder.getCard().getCardNumber(), OrderDTOMapping.dtoToOrder(orderDTO_Voucher).getCard().getCardNumber());

    }

    @Test
    void testDtoToCard(){
        OrderDTO orderDTO = new OrderDTO(card_credit, OrderType.NORMAL,0,0, new int[]{2},"Sergiu","Sergiu");

        assertEquals(card_credit.getCardNumber(),OrderDTOMapping.dtoToCard(orderDTO).getCardNumber());
        assertEquals(card_credit.getCiv(),OrderDTOMapping.dtoToCard(orderDTO).getCiv());
        assertEquals(card_credit.getBalance(),OrderDTOMapping.dtoToCard(orderDTO).getBalance());
        assertEquals(card_credit.getMonth(),OrderDTOMapping.dtoToCard(orderDTO).getMonth());
        assertEquals(card_credit.getYear(),OrderDTOMapping.dtoToCard(orderDTO).getYear());
        assertEquals(card_credit.getCardHolderName(),OrderDTOMapping.dtoToCard(orderDTO).getCardHolderName());
    }

    @Test
    void testDtoToPurchaseReceipt(){
         OrderDTO orderDTO = new OrderDTO(card_credit, OrderType.NORMAL,0,0, new int[]{2},"Sergiu","Sergiu@Endava.ro");
        LocalDateTime dateTime = LocalDateTime.now();
        PurchaseReceipt purchaseReceipt = new PurchaseReceipt("Sergiu","Sergiu@Endava.ro",0,"Payed by card: 4263982640269299", LocalDateTime.now());

        assertEquals(purchaseReceipt.getCustomerName(),OrderDTOMapping.dtoToPurchaseReceipt(orderDTO).getCustomerName());
        assertEquals(purchaseReceipt.getCustomerEmail(),OrderDTOMapping.dtoToPurchaseReceipt(orderDTO).getCustomerEmail());
        assertEquals(purchaseReceipt.getPaymentDetails(),OrderDTOMapping.dtoToPurchaseReceipt(orderDTO).getPaymentDetails());
        assertEquals(purchaseReceipt.getDateTime().getYear(),OrderDTOMapping.dtoToPurchaseReceipt(orderDTO).getDateTime().getYear());
        assertEquals(purchaseReceipt.getDateTime().getMonth(),OrderDTOMapping.dtoToPurchaseReceipt(orderDTO).getDateTime().getMonth());
        assertEquals(purchaseReceipt.getDateTime().getHour(),OrderDTOMapping.dtoToPurchaseReceipt(orderDTO).getDateTime().getHour());
        assertEquals(purchaseReceipt.getDateTime().getMinute(),OrderDTOMapping.dtoToPurchaseReceipt(orderDTO).getDateTime().getMinute());
    }
}
