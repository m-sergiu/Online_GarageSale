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

    public String createPurchase() {
        purchaseRepository = new PurchaseRepositoryImpl();
        return purchaseRepository.createPurchase();
    }

    public List<Asset> getAll(){
        return purchaseRepository.getAll();
    }

    public String addAsset(Asset asset){
        return purchaseRepository.addAsset(asset);
    }

    public String finalizePurchase(){
        return purchaseRepository.finalizePurchase();
    }
}
