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
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.verify;

@SpringBootTest
class AssetServiceTest {

    @Mock
    private AssetRepository assetRepository;
    private AssetServiceImpl assetService;

    Asset asset_1 = new Asset(1L,Category.LAPTOP,100,List.of(new Issue("none")),1);
    Asset asset_2 = new Asset(3L,Category.MOUSE,10,List.of(new Issue("none")),1);
    List<Asset> assetList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        assetService = new AssetServiceImpl(assetRepository);
        assetList.add(asset_1);
        assetList.add(asset_2);
    }
    @Test
    void testGetAllAssets(){
        Mockito.when(assetRepository.findAll()).thenReturn(assetList);
        assertEquals(assetList,assetService.findAll());
    }

    @Test
    void testGetAllAssetsAvailable(){
        Mockito.when(assetRepository.findByQuantityGreaterThan(0)).thenReturn(assetList);
        assertEquals(assetList,assetService.findAllAvailable());
    }

    @Test
    void testCreateAsset(){
        AssetDTO assetDTO = new AssetDTO(Category.LAPTOP,10,List.of(new Issue("none")),1);

        assetService.createAsset(assetDTO);

        ArgumentCaptor<Asset> assetArgumentCaptor = ArgumentCaptor.forClass(Asset.class);

        verify(assetRepository).save(assetArgumentCaptor.capture());

        Asset capturedAsset = assetArgumentCaptor.getValue();

        assertThat(capturedAsset.getId()).isEqualTo(AssetDTOMapping.dtoToAsset(assetDTO).getId());

        assertThat(capturedAsset.getCategory()).isEqualTo(AssetDTOMapping.dtoToAsset(assetDTO).getCategory());

        assertThat(capturedAsset.getPrice()).isEqualTo(AssetDTOMapping.dtoToAsset(assetDTO).getPrice());

        assertThat(capturedAsset.getQuantity()).isEqualTo(AssetDTOMapping.dtoToAsset(assetDTO).getQuantity());

        Asset asset = AssetDTOMapping.dtoToAsset(assetDTO);
        assertEquals(asset.getId(), assetService.createAsset(assetDTO).getId());
        assertEquals(asset.getCategory(), assetService.createAsset(assetDTO).getCategory());
        assertEquals(asset.getQuantity(), assetService.createAsset(assetDTO).getQuantity());
        assertEquals(asset.getPrice(), assetService.createAsset(assetDTO).getPrice());
    }

    @Test
    void testFindById(){
        Long id = 1L;
        Mockito.when(assetRepository.findById(id)).thenReturn(java.util.Optional.of(asset_1));
        assertEquals(asset_1,assetService.findById(id));
    }
    @Test
    void testFindById_throwErrorWhenIdEmpty(){
        Long id = 1L;
        Mockito.when(assetRepository.findById(id)).thenReturn(Optional.empty());
         assertThatThrownBy(() -> assetService.findById(id))
                .isInstanceOf(ProductDoesntExistException.class)
                .hasMessageContaining("Product id is invalid: -> " + id);
    }

}