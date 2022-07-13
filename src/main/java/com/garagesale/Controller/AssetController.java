package com.garagesale.Controller;

import com.garagesale.enums.Category;
import com.garagesale.service.AssetService;
import com.garagesale.service.AssetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.garagesale.domain.Asset;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/Asset")
public class AssetController {

    private final AssetService assetService;

    @Autowired
    public AssetController(AssetServiceImpl assetService){
        this.assetService = assetService;
    }

    @RequestMapping(value = "/showAssets")
    public List<Asset> getAssets(){
        return assetService.getAssets();
    }


}
