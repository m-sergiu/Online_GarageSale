package com.garagesale.repository;

import com.garagesale.domain.Asset;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("assetRepository")
public class AssetRepositoryImpl implements AssetRepository {
    public List<Asset> assets;

    public AssetRepositoryImpl() {
        assets = new ArrayList<>();
    }

    @Override
    public List<Asset> findAll() {
        return assets;
    }

    @Override
    public Asset createAsset(Asset asset) {
        assets.add(asset);
        return asset;
    }

}
