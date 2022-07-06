package com.garagesale.domain;

public class ProductAlreadyInCartException extends Exception {
    public ProductAlreadyInCartException(String errorMessage) {
        super(errorMessage);
    }
}
