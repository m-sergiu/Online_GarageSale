package com.garagesale.exceptions;

public class OrderDoesNotExistException extends RuntimeException {
    public OrderDoesNotExistException(String errorMessage){super(errorMessage);}
}
