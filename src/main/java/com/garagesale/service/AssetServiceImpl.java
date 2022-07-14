package com.garagesale.service;

import com.garagesale.repository.AssetRepository;
import com.garagesale.repository.HibernateAssetRepositoryImpl;
import com.garagesale.domain.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("assetService")
public class AssetServiceImpl implements AssetService {

    private AssetRepository assetRepository;
    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public void setAssetRepository(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    public List<Asset> findAll(){
       return assetRepository.findAll();
   }


}
