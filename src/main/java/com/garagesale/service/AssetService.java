package com.garagesale.service;

import com.garagesale.domain.Asset;

import java.util.List;

public interface AssetService {
    List<Asset> getAll();
    List<Asset> getAllAvailable();

}
