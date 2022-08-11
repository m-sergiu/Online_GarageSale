package com.garagesale.domain;


import com.garagesale.domain.Card;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "DEBIT_CARD")
public class DebitCard extends Card {
    public DebitCard() {
        super.setBalance(10);
    }
}
