package com.garagesale.repository;

import com.garagesale.domain.Asset;
import com.garagesale.enums.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("purchaseRepository")
public class PurchaseRepositoryImpl implements PurchaseRepository {
    public List<Asset> purchaseCart;


    @Override
    public String createPurchase() {
        purchaseCart = new ArrayList<>();
        return "Purchase shopping Cart created";
    }

    @Override
    public String addAssetToCart() {
        Asset keyboard = new Asset();
        keyboard.setAssetName("Keyboard DellKm7321");
        keyboard.setId(10);
        keyboard.setCategory(Category.KEYBOARD);
        keyboard.setQuantity(1);
        keyboard.setPrice(45.0d);
        keyboard.setIssues(new String[]{"Alt key is not working"});
        purchaseCart.add(keyboard);

        Asset asset = new Asset();
        asset.setAssetName("Asset");
        asset.setId(11);
        asset.setCategory(Category.HEADPHONES);
        asset.setQuantity(1);
        asset.setPrice(10.0);
        asset.setIssues(new String[]{" - "});
        purchaseCart.add(asset);
        return keyboard.getAssetName() + ", " + asset.getAssetName() + " added to the cart ";
    }

    @Override
    public List<Asset> getAll() {
        return purchaseCart;
    }

    @Override
    public List<Asset> finalizePurchase() {
        return purchaseCart;
    }
}
