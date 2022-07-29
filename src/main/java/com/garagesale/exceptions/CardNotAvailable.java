package com.garagesale.exceptions;

public class CardNotAvailable extends Exception{
    public CardNotAvailable(String errorMessage) {
        super(errorMessage);
    }
}
