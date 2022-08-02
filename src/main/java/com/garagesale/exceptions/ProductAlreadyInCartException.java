package com.garagesale.exceptions;

public class ProductAlreadyInCartException extends RuntimeException {
    public ProductAlreadyInCartException(String errorMessage) {
        super(errorMessage);
    }
}
