package com.garagesale.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "CREDIT_CARD")
public class CreditCard extends Card {
    public CreditCard() {
        super.setBalance(50000);
    }
}
