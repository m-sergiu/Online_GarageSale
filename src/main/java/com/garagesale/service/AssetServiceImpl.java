package com.garagesale.service;

import com.garagesale.dto.AssetDTO;
import com.garagesale.mapping.AssetDTOMapping;
import com.garagesale.domain.Asset;
import com.garagesale.repository.AssetRepository;
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
    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    @Override
    public List<Asset> findAllAvailable(){
        List<Asset> list = new ArrayList<>(assetRepository.findAll());
        for(Asset asset: assetRepository.findAll()){
            if (asset.getQuantity() < 1){
                list.remove(asset);
            }
        }
        return list;
    }

    @Override
    public Asset createAsset(AssetDTO assetDTO){
        Asset asset = AssetDTOMapping.dtoToAsset(assetDTO);
        asset.setId(returnLastId());
        return assetRepository.createAsset(asset);
    }

    public int returnLastId(){
        return assetRepository.findAll().size() + 1;
    }

}
