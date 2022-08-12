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
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

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
    private AssetServiceImpl underTest;


    @BeforeEach
    void setUp(){
        underTest = new AssetServiceImpl(assetRepository);

        Asset asset_1 = new Asset(1L,Category.LAPTOP,100,List.of(new Issue("none")),1);
        Asset asset_2 = new Asset(3L,Category.MOUSE,10,List.of(new Issue("none")),1);
        List<Asset> list = List.of(asset_1, asset_2);

        Mockito.when(assetRepository.findById(asset_1.getId())).thenReturn(java.util.Optional.of(asset_1));
    }
    @Test
    void canGetAllAssets(){
        underTest.findAll();
        verify(assetRepository).findAll();
    }

    @Test
    void canGetAllAssetsAvailable(){
        underTest.findAllAvailable();
        verify(assetRepository).findByQuantityGreaterThan(0);
    }

    @Test
    void canCreateAsset(){
        AssetDTO assetDTO = new AssetDTO(Category.LAPTOP,10,List.of(new Issue("none")),1);

        underTest.createAsset(assetDTO);

        ArgumentCaptor<Asset> assetArgumentCaptor = ArgumentCaptor.forClass(Asset.class);

        verify(assetRepository).save(assetArgumentCaptor.capture());

        Asset capturedAsset = assetArgumentCaptor.getValue();

        assertThat(capturedAsset.getId()).isEqualTo(AssetDTOMapping.dtoToAsset(assetDTO).getId());
    }

    @Test
    void canFindById(){
        Long id = 1L;
        Asset asset = underTest.findById(id);
        assertEquals(id,asset.getId());
    }
    @Test
    void canFindById_throwErrorWhenIdEmpty(){
        Long id = 2L;
        assertThatThrownBy(() -> underTest.findById(id))
                .isInstanceOf(ProductDoesntExistException.class)
                .hasMessageContaining("Product id is invalid. " + id);

    }

}