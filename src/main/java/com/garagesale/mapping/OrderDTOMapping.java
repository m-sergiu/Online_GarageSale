package com.garagesale.mapping;

import com.garagesale.domain.Card;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.Order;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;

public class OrderDTOMapping {
    private OrderDTOMapping() {
    }

    public static Order dtoToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomerName(orderDTO.getCustomerName());
        order.setCustomerEmail(orderDTO.getCustomerEmail());
        order.setCard(dtoToCreditCard(orderDTO));
        return order;
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
        return purchaseReceipt;
    }
}
