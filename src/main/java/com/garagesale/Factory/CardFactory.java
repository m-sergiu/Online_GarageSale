package com.garagesale.Factory;

import com.garagesale.domain.Cards.Card;
import com.garagesale.enums.CardType;
import com.garagesale.exceptions.CardNotAvailableException;

public abstract class CardFactory {

    public static CardFactory getCardFactory(char c){
        if(c == '4'){
            return new VisaFactory();
        }
        else if(c == '5'){
            return new MasterCardFactory();
        }
        else throw new CardNotAvailableException("Card number is not good");
    }

    public abstract Card getCard(CardType cardType);
}
