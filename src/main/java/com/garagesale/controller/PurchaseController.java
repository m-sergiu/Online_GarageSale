package com.garagesale.controller;

import com.garagesale.domain.Asset;
import com.garagesale.repository.PurchaseRepository;
import com.garagesale.repository.PurchaseRepositoryImpl;
import com.garagesale.service.PurchaseService;
import com.garagesale.service.PurchaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public String createPurchase() {
        PurchaseRepository purchaseRepo = new PurchaseRepositoryImpl();
        purchaseService = new PurchaseServiceImpl(purchaseRepo);
        return purchaseService.createPurchase();
    }

    @RequestMapping("/getAll")
    public List<Asset> getAllAssetsInCart() {
        return purchaseService.getAll();
    }

    @RequestMapping("/addAssetToCart")
    public String addAssetToPurchaseCart() {
        return purchaseService.addAssetToCart();
    }

    @RequestMapping("/finalizePurchase")
    public String finalizePurchase() {
        return purchaseService.finalizePurchase();
    }
}
