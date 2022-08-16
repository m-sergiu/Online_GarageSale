package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Issue;
import com.garagesale.dto.AssetDTO;
import com.garagesale.enums.Category;
import com.garagesale.exceptions.ProductDoesntExistException;
import com.garagesale.mapping.AssetDTOMapping;
import com.garagesale.repository.AssetRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class AssetServiceTest {

    @Mock
    private AssetRepository assetRepositoryMock;
    private AssetServiceImpl assetService;

    private Asset asset_1 = new Asset(1L,Category.LAPTOP,100,List.of(new Issue("none")),1);
    private Asset asset_2 = new Asset(3L,Category.MOUSE,10,List.of(new Issue("none")),1);
    private List<Asset> assetList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        assetService = new AssetServiceImpl(assetRepositoryMock);
        assetList.add(asset_1);
        assetList.add(asset_2);
    }
    @Test
    void testGetAllAssets(){
        Mockito.when(assetRepositoryMock.findAll()).thenReturn(assetList);
        assertEquals(assetList,assetService.findAll());
    }

    @Test
    void testGetAllAssetsAvailable(){
        Mockito.when(assetRepositoryMock.findByQuantityGreaterThan(0)).thenReturn(assetList);
        assertEquals(assetList,assetService.findAllAvailable());
    }

    @Test
    void testCreateAsset(){
        AssetDTO assetDTO = new AssetDTO(Category.LAPTOP,10,List.of(new Issue("none")),1);

        Asset asset = AssetDTOMapping.dtoToAsset(assetDTO);

        assertEquals(asset.getId(), assetService.createAsset(assetDTO).getId());
        assertEquals(asset.getCategory(), assetService.createAsset(assetDTO).getCategory());
        assertEquals(asset.getQuantity(), assetService.createAsset(assetDTO).getQuantity());
        assertEquals(asset.getPrice(), assetService.createAsset(assetDTO).getPrice());
    }

    @Test
    void testFindById(){
        Long id = 1L;
        Mockito.when(assetRepositoryMock.findById(id)).thenReturn(java.util.Optional.of(asset_1));
        assertEquals(asset_1,assetService.findById(id));
    }
    @Test
    void testFindById_throwErrorWhenIdEmpty(){
        Long id = 1L;
        Mockito.when(assetRepositoryMock.findById(id)).thenReturn(Optional.empty());
         assertThatThrownBy(() -> assetService.findById(id))
                .isInstanceOf(ProductDoesntExistException.class)
                .hasMessageContaining("Product id is invalid: -> " + id);
    }

}