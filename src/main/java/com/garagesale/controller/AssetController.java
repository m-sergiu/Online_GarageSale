package com.garagesale.controller;

import com.garagesale.domain.Asset;
import com.garagesale.repository.AssetRepository;
import com.garagesale.repository.AssetRepositoryImpl;
import com.garagesale.service.AssetService;
import com.garagesale.service.AssetServiceImpl;
import com.garagesale.service.PurchaseService;
import com.garagesale.service.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/asset")
public class AssetController {

    private AssetService assetService;
    private PurchaseService purchaseService;

    @Autowired
    public AssetController(AssetServiceImpl assetService, PurchaseServiceImpl purchaseService) {
        this.assetService = assetService;
        this.purchaseService = purchaseService;
    }

    @RequestMapping(value = "/getAll")
    public List<Asset> findAll() {
        AssetRepository assetRepo = new AssetRepositoryImpl();
        assetService = new AssetServiceImpl(assetRepo);
        return assetService.findAll();
    }

    @RequestMapping(value="/purchase")
    public String createPurchase(){
       List<Asset> purchaseCart = new ArrayList<>(List.of(findAll().get(0), findAll().get(1)));
        return purchaseService.createPurchase(purchaseCart);
    }



}
