package com.garagesale.mapping;

import com.garagesale.domain.CreditCard;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;
import com.garagesale.domain.Card;

public class OrderDTOMapping {
    private OrderDTOMapping(){}

    public static Card dtoToCreditCard(OrderDTO orderDTO){
        Card card = new CreditCard();
        card.setCardNumber(orderDTO.getCardNumber());
        card.setCardHolderName(orderDTO.getCardHolderName());
        card.setCIV(orderDTO.getCIV());
        card.setExpiry(orderDTO.getExpiry());
        return card;
    }

    public static PurchaseReceipt dtoToPurchaseReceipt(OrderDTO orderDTO){
        PurchaseReceipt purchaseReceipt = new PurchaseReceipt();
        purchaseReceipt.setCustomerName(orderDTO.getCustomerName());
        return purchaseReceipt;
    }
}
