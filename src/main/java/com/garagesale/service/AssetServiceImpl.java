package com.garagesale.service;

import com.garagesale.repository.AssetRepository;
import com.garagesale.domain.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {
    @Autowired
    private AssetRepository assetRepository;

    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }


    @Override
    public List<Asset> getAssets() {
        return assetRepository.getAssets();
    }
}
