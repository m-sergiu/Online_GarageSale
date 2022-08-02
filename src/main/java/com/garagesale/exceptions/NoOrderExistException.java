package com.garagesale.exceptions;

public class NoOrderExistException extends RuntimeException {
    public NoOrderExistException(String errorMessage){super(errorMessage);}
}
