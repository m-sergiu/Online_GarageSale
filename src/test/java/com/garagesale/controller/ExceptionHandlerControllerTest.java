package com.garagesale.controller;

import com.garagesale.domain.*;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.Category;
import com.garagesale.enums.OrderType;
import com.garagesale.exceptions.CardNotAvailableException;
import com.garagesale.exceptions.ProductAlreadyInCartException;
import com.garagesale.exceptions.ProductDoesntExistException;
import com.garagesale.service.AssetServiceImpl;
import com.garagesale.service.ValidatorService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionHandlerControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private AssetServiceImpl assetService;

    private ValidatorService validatorService;

    Card card_1 = new CreditCard("4263982640269299","Sergiu","316",30,10);
    Card card_2 = new CreditCard("263982640269299","Sergiu","316",30,10);
    Asset asset_1 = new Asset(1L, Category.LAPTOP,100, List.of(new Issue("none")),1);
    Asset asset_2 = new Asset(2L, Category.LAPTOP,100, List.of(new Issue("none")),1);

    @Test
    void testCardNotAvailableException() throws Exception{
        OrderDTO orderDTO = new OrderDTO(card_2, OrderType.NORMAL,0,0, new int[]{1},"Sergiu","Sergiu");
        List<Asset> assetList = new ArrayList<>();
        assetList.add(asset_1);

        Mockito.when(assetService.findAllAvailable()).thenReturn(assetList);

        this.mvc.perform(post("/orders/pay")
                        .content(asJsonString(orderDTO))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof CardNotAvailableException));
    }

    @Test
    void testProductDoesntExistException() throws Exception{
        OrderDTO orderDTO = new OrderDTO(card_1, OrderType.NORMAL,0,0, new int[]{1},"Sergiu","Sergiu");
        Mockito.when(assetService.findAllAvailable()).thenReturn(new ArrayList<>());

        this.mvc.perform(post("/orders/pay")
                        .content(asJsonString(orderDTO))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductDoesntExistException));
    }
    @Test
    void testProductAlreadyInCartException() throws Exception{
        OrderDTO orderDTO = new OrderDTO(card_1, OrderType.NORMAL,0,0, new int[]{1,2},"Sergiu","Sergiu");
        List<Asset> assetList = new ArrayList<>();
        assetList.add(asset_1);
        assetList.add(asset_2);


        Mockito.when(assetService.findAllAvailable()).thenReturn(assetList);
        Mockito.when(assetService.findById(1L)).thenReturn(asset_1);
        Mockito.when(assetService.findById(2L)).thenReturn(asset_2);

        this.mvc.perform(post("/orders/pay")
                        .content(asJsonString(orderDTO))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ProductAlreadyInCartException));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
