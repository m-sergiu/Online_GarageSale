package com.garagesale.service;

import com.garagesale.domain.Card;
import org.springframework.stereotype.Service;

@Service
public class ValidatorService {
    public boolean validateCardDetails(Card card) {
        return card.getCardNumber().length() == 16 && card.getCiv().length() == 3 && card.getYear() < 100 && card.getMonth() < 13 && card.getMonth() > 0 && checkLuhnAlg(card.getCardNumber());
    }

    private boolean checkLuhnAlg(String cardNr) {
        int[] cardArray = new int[cardNr.length()];

        for (int i = 0; i < cardNr.length(); i++) {
            char c = cardNr.charAt(i);
            cardArray[i] = Integer.parseInt("" + c);
        }

        for (int i = cardArray.length - 2; i >= 0; i = i - 2) {
            int num = cardArray[i];
            num = num * 2;
            if (num > 9) {
                num = num % 10 + num / 10;
            }
            cardArray[i] = num;
        }

        int sum = 0;
        for (int i : cardArray) {
            sum += i;
        }
        return (sum % 10 == 0);
    }

}
