package com.garagesale.Factory;

import com.garagesale.DTO.AssetDTO;
import com.garagesale.domain.Asset;

public class AssetDTOFactory {
    private AssetDTOFactory(){};

    public static Asset dtoToAsset(AssetDTO assetDTO){
        Asset asset = new Asset();
        asset.setCategory(assetDTO.getCategory());
        asset.setQuantity(assetDTO.getQuantity());
        asset.setPrice(assetDTO.getPrice());
        asset.setIssues(assetDTO.getIssues());
        asset.setId(assetDTO.getId());
        return asset;
    }

    public static AssetDTO assetToDTO(Asset asset){
        AssetDTO assetDTO = new AssetDTO();
        assetDTO.setPrice(asset.getPrice());
        assetDTO.setCategory(asset.getCategory());
        assetDTO.setIssues(asset.getIssues());
        assetDTO.setQuantity(asset.getQuantity());
        assetDTO.setId(asset.getId());
        return assetDTO;

    }
}
