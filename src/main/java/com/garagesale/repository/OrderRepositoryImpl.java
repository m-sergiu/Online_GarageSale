package com.garagesale.repository;

import com.garagesale.domain.*;
import com.garagesale.enums.Category;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("orderRepository")
public class OrderRepositoryImpl implements OrderRepository {
    Order order;

    @Override
    public Order getOrder() {
        return order;
    }

    @Override
    public Map<Category,Asset> getOrderCart() {
        return order.getPurchaseCart();
    }

    @Override
    public Order createOrder() {
        order = new Order();
        order.setId(1);
        order.setPurchaseCart(new HashMap<>());
        return order;
    }

    @Override
    public Asset addAssetToCart(Asset asset) {
        order.addAssetToOrderCart(asset);
        return asset;
    }
}
