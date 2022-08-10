package com.garagesale.mapping;

import com.garagesale.Factory.CardFactory;
import com.garagesale.domain.Cards.Card;
import com.garagesale.domain.PurchaseOrder;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;

import java.time.LocalDateTime;

public class OrderDTOMapping {
    private OrderDTOMapping() {
    }

    public static PurchaseOrder dtoToOrder(OrderDTO orderDTO) {
        PurchaseOrder.Builder builder = new PurchaseOrder.Builder();
        builder.customerName(orderDTO.getCustomerName()).customerEmail(orderDTO.getCustomerEmail()).card(dtoToCreditCard(orderDTO)).dateTime(LocalDateTime.now());
        PurchaseOrder purchaseOrder = builder.build();
        return purchaseOrder;
    }

    public static Card dtoToCreditCard(OrderDTO orderDTO) {
        CardFactory factory = CardFactory.getCardFactory(orderDTO.getCard().getCardNumber().charAt(0));
        Card card = factory.getCard(orderDTO.getCard().getCardType());
        card.setCardHolderName(orderDTO.getCard().getCardHolderName());
        card.setCardNumber(orderDTO.getCard().getCardNumber());
        card.setCiv(orderDTO.getCard().getCiv());
        card.setYear(orderDTO.getCard().getYear());
        card.setMonth(orderDTO.getCard().getMonth());
        return card;
    }

    public static PurchaseReceipt dtoToPurchaseReceipt(OrderDTO orderDTO) {
        PurchaseReceipt purchaseReceipt = new PurchaseReceipt();
        purchaseReceipt.setCustomerName(orderDTO.getCustomerName());
        purchaseReceipt.setCustomerEmail(orderDTO.getCustomerEmail());
        purchaseReceipt.setDateTime(LocalDateTime.now());
        return purchaseReceipt;
    }

}
