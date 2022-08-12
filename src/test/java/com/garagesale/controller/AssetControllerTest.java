package com.garagesale.controller;

import com.garagesale.repository.AssetRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class AssetControllerTest {

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetController assetController;



}