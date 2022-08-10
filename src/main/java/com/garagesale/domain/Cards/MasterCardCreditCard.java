package com.garagesale.domain.Cards;

import com.garagesale.enums.CardType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MASTERCARD_CREDIT_CARD")
public class MasterCardCreditCard extends Card {
    public MasterCardCreditCard() {
        super.setBalance(50000);
        super.setCardType(CardType.CREDIT);
    }
}
