package com.garagesale.controller;

import com.garagesale.domain.Asset;
import com.garagesale.service.AssetService;
import com.garagesale.service.AssetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class AssetController {

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetServiceImpl assetService) {
        this.assetService = assetService;
    }

    @RequestMapping(value = "/getAll")
    public List<Asset> findAll() {
        return assetService.findAll();
    }

    @RequestMapping("/getAllAvailable")
    public List<Asset> findAllAvailable() {
        return assetService.findAllAvailable();
    }


}
