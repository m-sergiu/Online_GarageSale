package com.garagesale.mapping;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Issue;
import com.garagesale.dto.AssetDTO;
import com.garagesale.enums.Category;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AssetMapingTest {

    @Test
    void testDtoToAssetMap(){
        AssetDTO assetDTO = new AssetDTO(Category.LAPTOP,10, List.of(new Issue("issue")),1);
        Asset asset = new Asset();
        asset.setCategory(Category.LAPTOP);
        asset.setPrice(10);
        asset.setIssues(List.of(new Issue("issue")));
        asset.setQuantity(1);

        Asset testAsset = AssetDTOMapping.dtoToAsset(assetDTO);
        assertEquals(asset.getCategory(), testAsset.getCategory());
        assertEquals(asset.getPrice(), testAsset.getPrice());
        assertEquals(asset.getIssues().get(0).getDescription(), testAsset.getIssues().get(0).getDescription());
        assertEquals(asset.getQuantity(), testAsset.getQuantity());
    }

    @Test
    void testDtoToIssueMap(){
        AssetDTO assetDTO = new AssetDTO(Category.LAPTOP,10, List.of(new Issue("issue")),1);
        Asset asset = new Asset();
        List<Issue> issueList = List.of(new Issue("issue"));

        assertEquals(issueList.get(0).getDescription(),AssetDTOMapping.dtoToIssues(assetDTO, asset).get(0).getDescription());
    }
}
