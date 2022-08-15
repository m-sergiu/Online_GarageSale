package com.garagesale.controller;
import com.garagesale.domain.Card;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.OrderType;
import com.garagesale.service.OrderServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @MockBean
    private OrderServiceImpl orderService;
    @Autowired
    private MockMvc mvc;
    private OrderController orderController;

    @BeforeEach
    void setUp() {
        orderController = new OrderController(orderService);
    }

    @Test
    void testFinalizeOrder() throws Exception{
        Card card = new CreditCard("4263982640269299","Sergiu","316",30,10);
        OrderDTO orderDTO = new OrderDTO(card, OrderType.NORMAL,0,0, new int[]{2},"Sergiu","Sergiu");
        PurchaseReceipt purchaseReceipt = new PurchaseReceipt("Sergiu", "Sergiu",0, "payed by card: 4263982640269299", LocalDateTime.now());

        Mockito.when(orderService.finalizeOrder(orderDTO)).thenReturn(purchaseReceipt);

        assertEquals(purchaseReceipt,orderService.finalizeOrder(orderDTO));

        this.mvc.perform(post("/orders/pay")
                        .content(asJsonString(orderDTO))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
