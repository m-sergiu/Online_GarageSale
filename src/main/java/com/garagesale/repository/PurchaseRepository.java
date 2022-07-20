package com.garagesale.repository;

import com.garagesale.domain.Asset;

import java.util.List;

public interface PurchaseRepository {
    String createPurchase();
    String addAssetToCart();
    List<Asset> getAll();
    List<Asset> finalizePurchase();
}
