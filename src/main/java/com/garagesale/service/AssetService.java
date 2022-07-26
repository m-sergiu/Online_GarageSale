package com.garagesale.service;

import com.garagesale.domain.Asset;

import java.util.List;

public interface AssetService {
    List<Asset> findAll();
    List<Asset> findAllAvailable();

}
