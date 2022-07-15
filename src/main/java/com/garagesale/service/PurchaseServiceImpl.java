package com.garagesale.service;

import com.garagesale.domain.Asset;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Override
    public String createPurchase(List<Asset> assets) {
        List<Asset> purchaseCart = new ArrayList<>(assets);
        StringBuilder result = new StringBuilder();
        int balance = 0;
        for(Asset asset: purchaseCart){
            result.append(asset.getAssetName()).append(" ").append("-").append(asset.getPrice() + "$; ");
        }
        return result.toString();
    }
}
