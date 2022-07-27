package com.garagesale.service;

import com.garagesale.DTO.AssetDTO;
import com.garagesale.Factory.AssetDTOFactory;
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
        assetDTO.setId(AssetDTO.IDGenerator());
        Asset asset = AssetDTOFactory.dtoToAsset(assetDTO);
        return assetRepository.createAsset(asset);
    }


}
