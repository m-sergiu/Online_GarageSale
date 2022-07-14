package com.garagesale.repository;

import com.garagesale.domain.Asset;
import com.garagesale.enums.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository("assetRepository")
public class HibernateAssetRepositoryImpl implements AssetRepository {
    @Override
    public List<Asset> findAll(){
        List<Asset> assets = new ArrayList<>();

        Asset asset = new Asset();
        asset.setAssetName("Mouse Dell MS116");
        asset.setId(1);
        asset.setCategory(Category.MOUSE);
        asset.setQuantity(1);
        asset.setPrice(5.99d);
        asset.setIssues(new String[]{"click","right click"});

        assets.add(asset);
        return assets;
    }


}
