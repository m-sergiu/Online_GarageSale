package com.garagesale.domain.Cards;


import com.garagesale.enums.CardType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "MASTERCARD_DEBIT_CARD")
public class MasterCardDebitCard extends Card {
    public MasterCardDebitCard() {
        super.setBalance(100);
        super.setCardType(CardType.DEBIT);
    }
}
