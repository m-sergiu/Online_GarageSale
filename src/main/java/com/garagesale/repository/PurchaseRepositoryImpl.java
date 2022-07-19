package com.garagesale.repository;

import com.garagesale.controller.AssetController;
import com.garagesale.domain.Asset;
import com.garagesale.enums.Category;
import com.garagesale.service.AssetService;
import com.garagesale.service.AssetServiceImpl;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("purchaseRepository")
public class PurchaseRepositoryImpl implements PurchaseRepository{
    public List<Asset> purchaseCart;


    @Override
    public String createPurchase() {
        purchaseCart = new ArrayList<>();
        return "shopping Cart created";
    }

    @Override
    public String addAsset(Asset asset){
        //purchaseCart.add(asset);
        //add an asset
        Asset keyboard = new Asset();
        keyboard.setAssetName("Keyboard DellKm7321");
        keyboard.setId(10);
        keyboard.setCategory(Category.KEYBOARD);
        keyboard.setQuantity(1);
        keyboard.setPrice(45.0d);
        keyboard.setIssues(new String[]{"Alt key is not working"});
        purchaseCart.add(keyboard);
        return asset + "added to the cart";
    }

    @Override
    public List<Asset> getAll() {
        return purchaseCart;
    }

    public String finalizePurchase(){
       StringBuilder result = new StringBuilder();
        for(Asset asset: purchaseCart){
            result.append(asset.getAssetName())
                    .append(" - ")
                    .append(asset.getPrice() + "$; ");
        }
        return result.toString();
    }
}
