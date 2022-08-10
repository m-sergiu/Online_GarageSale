package com.garagesale.domain.Cards;

import com.garagesale.enums.CardType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "VISA_CREDIT_CARD")
public class VisaCreditCard extends Card {
    public VisaCreditCard() {
        super.setBalance(50000);
        super.setCardType(CardType.CREDIT);
    }
}
