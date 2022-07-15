package com.garagesale.controller;

import com.garagesale.domain.Asset;
import com.garagesale.repository.AssetRepository;
import com.garagesale.repository.AssetRepositoryImpl;
import com.garagesale.service.AssetService;
import com.garagesale.service.AssetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Asset")
public class AssetController {

    private AssetService assetService;

    @Autowired
    public AssetController(AssetServiceImpl assetService) {
        this.assetService = assetService;
    }

    @RequestMapping(value = "/getAll")
    public List<Asset> findAll() {
        AssetRepository assetRepo = new AssetRepositoryImpl();
        assetService = new AssetServiceImpl(assetRepo);
        return assetService.findAll();
    }


}
