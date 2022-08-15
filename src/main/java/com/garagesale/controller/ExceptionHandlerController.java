package com.garagesale.controller;

import com.garagesale.exceptions.CardNotAvailableException;
import com.garagesale.exceptions.ProductAlreadyInCartException;
import com.garagesale.exceptions.ProductDoesntExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler
    public ResponseEntity<Object> handleProductDoesntExistException(ProductDoesntExistException ex, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "Product id is invalid.");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleCardNotAvailable(CardNotAvailableException ex, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "Card details are not good, or not enough balance.");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleProductAlreadyInCartException(ProductAlreadyInCartException ex, WebRequest request){
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDate.now());
        body.put("message", "You already have a product of that category in the cart.");

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
