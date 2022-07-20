package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.repository.AssetRepository;
import com.garagesale.repository.AssetRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("assetService")
public class AssetServiceImpl implements AssetService {

    private AssetRepository assetRepository;

    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository) {

        this.assetRepository = assetRepository;
    }

    @Override
    public List<Asset> getAll() {
        assetRepository = new AssetRepositoryImpl();
        return assetRepository.getAll();
    }

    @Override
    public List<Asset> getAllAvailable(){
        List<Asset> list = new ArrayList<>(assetRepository.getAll());
        for(Asset asset: assetRepository.getAll()){
            if (asset.getQuantity() < 1){
                list.remove(asset);
            }
        }
        return list;
    }


}
