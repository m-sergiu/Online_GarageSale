package com.garagesale.mapping;

import com.garagesale.factory.AbstractOrderFactory;
import com.garagesale.factory.ProviderOrderFactory;
import com.garagesale.domain.Card;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.Orders.PurchaseOrder;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;

import java.time.LocalDateTime;

public class OrderDTOMapping {
    private OrderDTOMapping() {
    }

    public static PurchaseOrder dtoToOrder(OrderDTO orderDTO) {
        AbstractOrderFactory abstractFactory = ProviderOrderFactory.getInstance().getOrderFactory(orderDTO.getOrderType());
        return (PurchaseOrder) abstractFactory.create(orderDTO);
    }

    public static Card dtoToCreditCard(OrderDTO orderDTO) {
        Card card = new CreditCard();
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
