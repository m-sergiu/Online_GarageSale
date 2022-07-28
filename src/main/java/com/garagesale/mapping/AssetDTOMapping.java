package com.garagesale.mapping;

import com.garagesale.dto.AssetDTO;
import com.garagesale.domain.Asset;

public class AssetDTOMapping {
    private AssetDTOMapping(){}

    public static Asset dtoToAsset(AssetDTO assetDTO){
        Asset asset = new Asset();
        asset.setCategory(assetDTO.getCategory());
        asset.setQuantity(assetDTO.getQuantity());
        asset.setPrice(assetDTO.getPrice());
        asset.setIssues(assetDTO.getIssues());
        return asset;
    }

    public static AssetDTO assetToDTO(Asset asset){
        AssetDTO assetDTO = new AssetDTO();
        assetDTO.setPrice(asset.getPrice());
        assetDTO.setCategory(asset.getCategory());
        assetDTO.setIssues(asset.getIssues());
        assetDTO.setQuantity(asset.getQuantity());
        return assetDTO;

    }
}
