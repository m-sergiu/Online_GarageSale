package com.garagesale.controller;

import com.garagesale.DTO.AssetDTO;
import com.garagesale.domain.Asset;
import com.garagesale.service.AssetService;
import com.garagesale.service.AssetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class AssetController {

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetServiceImpl assetService) {
        this.assetService = assetService;
    }

    @GetMapping
    public List<Asset> findAll() {
        return assetService.findAll();
    }

    @GetMapping("/getAllAvailable")
    public List<Asset> findAllAvailable() {
        return assetService.findAllAvailable();
    }
    @PostMapping
    public Asset createAsset(@RequestBody AssetDTO assetDTO){
        return assetService.createAsset(assetDTO);
    }


}
