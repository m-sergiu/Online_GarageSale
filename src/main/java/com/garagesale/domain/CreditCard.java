package com.garagesale.domain;

import com.garagesale.enums.CardType;

public class CreditCard extends Card {
    public CreditCard() {
        super.setBalance(50000);
        super.setCardType(CardType.CREDITCARD);
    }
}
