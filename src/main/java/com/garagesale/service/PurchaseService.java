package com.garagesale.service;

import com.garagesale.domain.Asset;

import java.util.List;

public interface PurchaseService {

     String createPurchase();
     List<Asset> getAll();
     String addAssetToCart();
     String finalizePurchase();
}
