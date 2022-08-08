package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Issue;
import com.garagesale.dto.AssetDTO;

import java.util.List;
import java.util.Optional;

public interface AssetService {
    List<Asset> findAll();

    List<Asset> findAllAvailable();

    void createAsset(AssetDTO assetDTO);

    Asset findById(Long id);

}

