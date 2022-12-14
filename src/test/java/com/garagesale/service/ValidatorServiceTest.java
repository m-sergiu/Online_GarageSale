package com.garagesale.service;
import static org.junit.jupiter.api.Assertions.*;

import com.garagesale.domain.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ValidatorServiceTest {

    @Autowired
    private ValidatorService validatorService;

    @Test
    void testValidCardDetails(){
        Card card = new Card("4263982640269299","Sergiu","316",30,10);

        assertTrue(validatorService.validateCardDetails(card));
    }

    @Test
    void testInvalidCardDetails(){
        Card card = new Card("263982640269299","Sergiu","316",30,10);

        assertFalse(validatorService.validateCardDetails(card));
    }
    @Test
    void testInvalidCardNumber_usingLuhnAlg(){
        Card card = new Card("4163982640269299","Sergiu","316",30,10);

        assertFalse(validatorService.validateCardDetails(card));
    }

    @Test
    void testInvalidCiv(){
        Card card = new Card("4263982640269299","Sergiu","16",30,10);

        assertFalse(validatorService.validateCardDetails(card));
    }
    @Test
    void testInvalidYear(){
        Card card = new Card("4263982640269299","Sergiu","316",120,10);

        assertFalse(validatorService.validateCardDetails(card));
    }
    @Test
    void testInvalidMonth(){
        Card card = new Card("4263982640269299","Sergiu","316",120,13);

        assertFalse(validatorService.validateCardDetails(card));
    }
}
