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
    void testValidateCardDetails(){
        Card card = new Card("4263982640269299","Sergiu","316",30,10);

        assertTrue(validatorService.validateCardDetails(card));
    }
}
