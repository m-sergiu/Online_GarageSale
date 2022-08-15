package com.garagesale.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CREDIT_CARD")
public class CreditCard extends Card {
    public CreditCard() {
        super.setBalance(50000);
    }

    public CreditCard(String cardNumber, String cardHolderName, String civ, int year, int month) {
        super(cardNumber, cardHolderName, civ, year, month);
        super.setBalance(50000);
    }
}
