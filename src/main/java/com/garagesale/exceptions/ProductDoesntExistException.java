package com.garagesale.exceptions;

public class ProductDoesntExistException extends RuntimeException{
    public ProductDoesntExistException(String errorMessage){super(errorMessage);}
}
