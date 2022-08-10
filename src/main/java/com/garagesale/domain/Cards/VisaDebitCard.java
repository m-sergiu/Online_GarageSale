package com.garagesale.domain.Cards;


import com.garagesale.enums.CardType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "VISA_DEBIT_CARD")
public class VisaDebitCard extends Card {
    public VisaDebitCard() {
        super.setBalance(100);
        super.setCardType(CardType.DEBIT);
    }
}
