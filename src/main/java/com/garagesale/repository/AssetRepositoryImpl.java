package com.garagesale.repository;

import com.garagesale.domain.Asset;
import com.garagesale.enums.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("assetRepository")
public class AssetRepositoryImpl implements AssetRepository {
    List<Asset> assets;

    public AssetRepositoryImpl() {
        assets = new ArrayList<>();
        //Create assets
        Asset mouseDellMs116 = new Asset();
        mouseDellMs116.setAssetName("Mouse Dell MS116");
        mouseDellMs116.setId(1);
        mouseDellMs116.setCategory(Category.MOUSE);
        mouseDellMs116.setQuantity(0);
        mouseDellMs116.setPrice(5.99d);
        mouseDellMs116.setIssues(new String[]{"Wheel not working"});

        Asset laptopLenovoT570 = new Asset();
        laptopLenovoT570.setAssetName("Laptop LenovoT570");
        laptopLenovoT570.setId(2);
        laptopLenovoT570.setCategory(Category.LAPTOP);
        laptopLenovoT570.setQuantity(1);
        laptopLenovoT570.setPrice(200.0d);
        laptopLenovoT570.setIssues(new String[]{"Low battery health"});

        Asset monitorDellP2418D = new Asset();
        monitorDellP2418D.setAssetName("Monitor DellP2418D");
        monitorDellP2418D.setId(3);
        monitorDellP2418D.setCategory(Category.MONITOR);
        monitorDellP2418D.setQuantity(1);
        monitorDellP2418D.setPrice(70.0d);
        monitorDellP2418D.setIssues(new String[]{"1 pixel is dead", "Weak arm"});

        Asset keyboardRazerBlackwidow = new Asset();
        keyboardRazerBlackwidow.setAssetName("Keyboard Razer Blackwidow");
        keyboardRazerBlackwidow.setId(4);
        keyboardRazerBlackwidow.setCategory(Category.KEYBOARD);
        keyboardRazerBlackwidow.setQuantity(1);
        keyboardRazerBlackwidow.setPrice(70.0d);
        keyboardRazerBlackwidow.setIssues(new String[]{""});

        Asset headphonesJabraEvolve20 = new Asset();
        headphonesJabraEvolve20.setAssetName("Headphones Jabra Evolve20");
        headphonesJabraEvolve20.setId(5);
        headphonesJabraEvolve20.setCategory(Category.HEADPHONES);
        headphonesJabraEvolve20.setQuantity(1);
        headphonesJabraEvolve20.setPrice(50.0d);
        headphonesJabraEvolve20.setIssues(new String[]{""});

        Asset keyboardDellKm7321 = new Asset();
        keyboardDellKm7321.setAssetName("Keyboard DellKm7321");
        keyboardDellKm7321.setId(6);
        keyboardDellKm7321.setCategory(Category.KEYBOARD);
        keyboardDellKm7321.setQuantity(1);
        keyboardDellKm7321.setPrice(45.0d);
        keyboardDellKm7321.setIssues(new String[]{"Alt key is not working"});

        assets.add(mouseDellMs116);
        assets.add(laptopLenovoT570);
        assets.add(monitorDellP2418D);
        assets.add(keyboardRazerBlackwidow);
        assets.add(headphonesJabraEvolve20);
        assets.add(keyboardDellKm7321);
    }

    @Override
    public List<Asset> findAll() {
        return assets;
    }

    @Override
    public Asset createAsset(Asset asset) {
        Asset newAsset = new Asset();
        newAsset.setId(asset.getId());
        newAsset.setAssetName(asset.getAssetName());
        newAsset.setIssues(asset.getIssues());
        newAsset.setPrice(asset.getPrice());
        newAsset.setQuantity(asset.getQuantity());
        newAsset.setCategory(asset.getCategory());
        assets.add(asset);
        return newAsset;
    }
}
