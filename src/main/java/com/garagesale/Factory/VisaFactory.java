package com.garagesale.Factory;

import com.garagesale.domain.Cards.Card;
import com.garagesale.domain.Cards.VisaCreditCard;
import com.garagesale.domain.Cards.VisaDebitCard;
import com.garagesale.enums.CardType;

public class VisaFactory extends CardFactory {
    @Override
    public Card getCard(CardType cardType) {
        switch (cardType) {
            case DEBIT:
                return new VisaDebitCard();
            case CREDIT:
                return new VisaCreditCard();
            default:
                break;
        }
        return null;
    }
}
