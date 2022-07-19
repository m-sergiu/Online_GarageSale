package com.garagesale.controller;

import com.garagesale.domain.Asset;
import com.garagesale.enums.Category;
import com.garagesale.repository.PurchaseRepository;
import com.garagesale.repository.PurchaseRepositoryImpl;
import com.garagesale.service.PurchaseService;
import com.garagesale.service.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/purchase")
public class PurchaseController {
    private PurchaseService purchaseService;

    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    @RequestMapping("/createPurchase")
    public String createPurchase(){
        PurchaseRepository purchaseRepo = new PurchaseRepositoryImpl();
        purchaseService = new PurchaseServiceImpl(purchaseRepo);
        return purchaseService.createPurchase();
    }
    @RequestMapping("/getAll")
    public List<Asset> getAll(){
        return purchaseService.getAll();
    }

    @RequestMapping("/addAsset")
    public List<Asset> addAsset(Asset asset){
        purchaseService.addAsset(asset);
        return purchaseService.getAll();
    }
    @RequestMapping("/finalizePurchase")
    public String finalizePurchase(){
        return purchaseService.finalizePurchase();
    }
}
