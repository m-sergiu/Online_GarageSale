package com.garagesale.mapping;

import com.garagesale.domain.Asset;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;
import com.garagesale.domain.Card;

import java.util.ArrayList;
import java.util.List;

public class OrderDTOMapping {
    private OrderDTOMapping(){}

    public static Card dtoToCreditCard(OrderDTO orderDTO){
        Card card = new CreditCard();
        card.setCardHolderName(orderDTO.getCard().getCardHolderName());
        card.setCardNumber(orderDTO.getCard().getCardNumber());
        card.setCiv(orderDTO.getCard().getCiv());
        card.setYear(orderDTO.getCard().getYear());
        card.setMonth(orderDTO.getCard().getMonth());
        return card;
    }
    public static PurchaseReceipt dtoToPurchaseReceipt(OrderDTO orderDTO){
        PurchaseReceipt purchaseReceipt = new PurchaseReceipt();
        purchaseReceipt.setCustomerName(orderDTO.getCustomerName());
        return purchaseReceipt;
    }
}
