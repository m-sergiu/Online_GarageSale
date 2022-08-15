package com.garagesale.controller;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Issue;
import com.garagesale.dto.AssetDTO;
import com.garagesale.enums.Category;
import com.garagesale.service.AssetServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AssetControllerTest {

    @MockBean
    private AssetServiceImpl assetService;
    @Autowired
    private MockMvc mvc;
    private AssetController assetController;

    @BeforeEach
    void setUp() {
        assetController = new AssetController(assetService);
    }

    @Test
    void testGetAllAvailableAssets() throws Exception {
        Asset asset = new Asset( 1L, Category.LAPTOP, 10, List.of(new Issue("issue")), 1);

        List<Asset> assetList = new ArrayList<>();
        assetList.add(asset);

        Mockito.when(assetService.findAllAvailable()).thenReturn(assetList);

        this.mvc.perform(get("/products")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(asset.getId()))
                .andExpect(jsonPath("$[0].price").value(asset.getPrice()));

    }

    @Test
    void testGetAllAssets() throws Exception {
        Asset asset_1 = new Asset( 1L, Category.LAPTOP, 10, List.of(new Issue("issue")), 1);
        Asset asset_2 = new Asset( 2L, Category.MOUSE, 10, List.of(new Issue(null)), 0);

        List<Asset> assetList = new ArrayList<>();
        assetList.add(asset_1);
        assetList.add(asset_2);

        Mockito.when(assetService.findAll()).thenReturn(assetList);

        this.mvc.perform(get("/products/getAll")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].id").value(asset_1.getId()))
                .andExpect(jsonPath("$[0].price").value(asset_1.getPrice()));

    }

    @Test
    void testCreateAsset() throws Exception {
        Asset asset = new Asset( 1L, Category.LAPTOP, 10, List.of(new Issue("issue")), 1);
        AssetDTO assetDTO = new AssetDTO(Category.LAPTOP,10,List.of(new Issue("issue")),1);

        Mockito.when(assetService.createAsset(assetDTO)).thenReturn(asset);

        assertEquals(asset, assetService.createAsset(assetDTO));

        this.mvc.perform(post("/products")
                        .content(asJsonString(assetDTO))
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isCreated());

    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



}