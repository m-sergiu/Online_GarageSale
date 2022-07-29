package com.garagesale.domain;

import com.garagesale.enums.CardType;

public class DebitCard extends Card{
    public DebitCard() {
        super.setBalance(0);
        super.setCardType(CardType.DEBITCARD);
    }
}
