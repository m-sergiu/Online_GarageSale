package com.garagesale.controller;

import com.garagesale.domain.Asset;
import com.garagesale.dto.AssetDTO;
import com.garagesale.service.AssetService;
import com.garagesale.service.AssetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Asset> findAllAvailable() {
        return assetService.findAllAvailable();
    }

    @GetMapping("/getAll")
    public List<Asset> findAll() {
       return assetService.findAll();
    }

    @PostMapping
    public ResponseEntity<Asset> createAsset(@RequestBody AssetDTO assetDTO) {
        Asset asset = assetService.createAsset(assetDTO);
        return new ResponseEntity<Asset>(asset, HttpStatus.CREATED);
    }


}
