package com.garagesale.mapping;

import com.garagesale.domain.Card;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.PurchaseOrder;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;

import java.time.LocalDateTime;

public class OrderDTOMapping {
    private OrderDTOMapping() {
    }

    public static PurchaseOrder dtoToOrder(OrderDTO orderDTO) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setCustomerName(orderDTO.getCustomerName());
        purchaseOrder.setCustomerEmail(orderDTO.getCustomerEmail());
        purchaseOrder.setCard(dtoToCreditCard(orderDTO, purchaseOrder));
        purchaseOrder.setDateTime(LocalDateTime.now());
        return purchaseOrder;
    }

    public static Card dtoToCreditCard(OrderDTO orderDTO, PurchaseOrder purchaseOrder) {
        Card card = new CreditCard();
        card.setCardHolderName(orderDTO.getCard().getCardHolderName());
        card.setCardNumber(orderDTO.getCard().getCardNumber());
        card.setCiv(orderDTO.getCard().getCiv());
        card.setYear(orderDTO.getCard().getYear());
        card.setMonth(orderDTO.getCard().getMonth());
        card.setPurchaseOrder(purchaseOrder);
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
