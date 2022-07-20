package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.repository.PurchaseRepository;
import com.garagesale.repository.PurchaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    private PurchaseRepository purchaseRepository;
@Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    public String createPurchase() {
        purchaseRepository = new PurchaseRepositoryImpl();
        return purchaseRepository.createPurchase();
    }
    @Override
    public List<Asset> getAll(){
        return purchaseRepository.getAll();
    }
    @Override
    public String addAssetToCart() {
        return purchaseRepository.addAssetToCart();
    }
    @Override
    public String finalizePurchase() {
        List<Asset> list = purchaseRepository.finalizePurchase();
        StringBuilder result = new StringBuilder();
        int totalBalance = 0;
        result.append("Thank you for the purchase. The item list: ");
        for (Asset asset : list) {
            //Add to total price
            totalBalance += asset.getPrice();
            result.append(asset.getAssetName() + " - $" + asset.getPrice() + "; ");

        }
        result.append(" Total balance: $" + totalBalance + ". Payment details: by card");
        return result.toString();
    }
}
