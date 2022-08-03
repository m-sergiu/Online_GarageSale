package com.garagesale.mapping;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Issues;
import com.garagesale.dto.AssetDTO;

import java.util.ArrayList;
import java.util.List;

public class AssetDTOMapping {

    public static Asset dtoToAsset(AssetDTO assetDTO) {
        Asset asset = new Asset();
        asset.setCategory(assetDTO.getCategory());
        asset.setQuantity(assetDTO.getQuantity());
        asset.setPrice(assetDTO.getPrice());
        asset.setIssues(dtoToIssues(assetDTO));
        return asset;
    }

    public static List<Issues> dtoToIssues(AssetDTO assetDTO) {
        List<Issues> list = new ArrayList<>();
        for (Issues DTOissue : assetDTO.getIssues()) {
            Issues issues = new Issues();
            issues.setDescription(DTOissue.getDescription());
            list.add(issues);
        }
        return list;
    }
}
