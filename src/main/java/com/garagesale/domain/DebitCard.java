package com.garagesale.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "DEBIT_CARD")
public class DebitCard extends Card {
    public DebitCard() {
        super.setBalance(10);
    }
}
