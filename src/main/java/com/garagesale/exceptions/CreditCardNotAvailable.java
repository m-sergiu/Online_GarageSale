package com.garagesale.exceptions;

public class CreditCardNotAvailable extends Exception{
    public CreditCardNotAvailable(String errorMessage) {
        super(errorMessage);
    }
}
