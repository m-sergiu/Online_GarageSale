package com.garagesale.domain;

import org.springframework.stereotype.Component;

import java.util.List;


public class Garage {
    private List<Asset> assetList;

    public Garage(List<Asset> assetList) {
        this.assetList = assetList;
    }

    public List<Asset> getAssetList() {
        return this.assetList;
    }

    public void setAssetList(List<Asset> assetList) {
        this.assetList = assetList;
    }

    public void removeAssetFromGarage(Asset asset) {
        this.assetList.remove(asset);
    }
}
