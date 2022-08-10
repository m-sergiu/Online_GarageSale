package com.garagesale.Factory;

import com.garagesale.domain.Cards.*;
import com.garagesale.enums.CardType;

public class MasterCardFactory extends CardFactory{
    @Override
    public Card getCard(CardType cardType) {
        switch (cardType) {
            case DEBIT:
                return new MasterCardDebitCard();
            case CREDIT:
                return new MasterCardCreditCard();
            default:
                break;
        }
        return null;
    }
}
