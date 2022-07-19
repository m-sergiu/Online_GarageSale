package com.garagesale.repository;

import com.garagesale.domain.Asset;

import java.util.List;

public interface PurchaseRepository {
    String createPurchase();
    String addAsset(Asset asset);
    List<Asset> getAll();
    String finalizePurchase();
}
