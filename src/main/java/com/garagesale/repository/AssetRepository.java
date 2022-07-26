package com.garagesale.repository;

import com.garagesale.domain.Asset;

import java.util.List;

public interface AssetRepository {
    List<Asset> findAll();
    Asset createAsset(Asset asset);
}
