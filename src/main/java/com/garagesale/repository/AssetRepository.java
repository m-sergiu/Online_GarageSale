package com.garagesale.repository;

import com.garagesale.domain.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AssetRepository extends JpaRepository<Asset, Long> {
    public List<Asset> findByQuantityGreaterThan(int quantity);
}
