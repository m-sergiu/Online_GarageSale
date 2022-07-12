package com.garagesale.exceptions;

public class ProductAlreadyInCartException extends Exception {
    public ProductAlreadyInCartException(String errorMessage) {
        super(errorMessage);
    }
}
