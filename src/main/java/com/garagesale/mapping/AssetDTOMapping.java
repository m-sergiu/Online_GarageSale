package com.garagesale.mapping;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Issue;
import com.garagesale.dto.AssetDTO;

import java.util.ArrayList;
import java.util.List;

public class AssetDTOMapping {

    public static Asset dtoToAsset(AssetDTO assetDTO) {
        Asset asset = new Asset();
        asset.setCategory(assetDTO.getCategory());
        asset.setQuantity(assetDTO.getQuantity());
        asset.setPrice(assetDTO.getPrice());
        asset.setIssues(dtoToIssues(assetDTO, asset));
        return asset;
    }

    public static List<Issue> dtoToIssues(AssetDTO assetDTO, Asset asset) {
        List<Issue> list = new ArrayList<>();
        for (Issue DTOissue : assetDTO.getIssues()) {
            Issue issue = new Issue();
            issue.setDescription(DTOissue.getDescription());
            issue.setAsset(asset);
            list.add(issue);
        }
        return list;
    }
}
