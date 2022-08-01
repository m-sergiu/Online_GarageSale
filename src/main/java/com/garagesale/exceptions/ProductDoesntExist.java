package com.garagesale.exceptions;

public class ProductDoesntExist extends Exception{
    public ProductDoesntExist(String errorMessage){super(errorMessage);}
}
