package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.dto.AssetDTO;
import com.garagesale.exceptions.ProductDoesntExistException;
import com.garagesale.mapping.AssetDTOMapping;
import com.garagesale.repository.AssetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("assetService")
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;

    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public List<Asset> findAll() {
        return assetRepository.findAll();
    }

    @Override
    public List<Asset> findAllAvailable() {
        return assetRepository.findByQuantityGreaterThan(0);
    }

    @Override
    public void createAsset(AssetDTO assetDTO) {
        Asset asset = AssetDTOMapping.dtoToAsset(assetDTO);
        assetRepository.save(asset);
    }

    @Override
    public Asset findById(Long id) {
        Optional<Asset> optionalAsset = assetRepository.findById(id);
        if (optionalAsset.isEmpty()) {
            throw new ProductDoesntExistException("product id is invalid: " + id);
        }
        return optionalAsset.get();
    }

}
