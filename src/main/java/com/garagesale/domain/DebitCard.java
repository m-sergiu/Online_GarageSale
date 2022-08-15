package com.garagesale.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "DEBIT_CARD")
public class DebitCard extends Card {
    public DebitCard() {
        super.setBalance(10);
    }

    public DebitCard(String cardNumber, String cardHolderName, String civ, int year, int month) {
        super(cardNumber, cardHolderName, civ, year, month);
        super.setBalance(10);
    }
}
