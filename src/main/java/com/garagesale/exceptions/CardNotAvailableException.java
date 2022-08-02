package com.garagesale.exceptions;

public class CardNotAvailableException extends RuntimeException{
    public CardNotAvailableException(String errorMessage) {
        super(errorMessage);
    }
}
