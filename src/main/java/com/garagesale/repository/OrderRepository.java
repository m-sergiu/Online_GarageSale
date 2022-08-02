package com.garagesale.repository;

import com.garagesale.domain.*;
import com.garagesale.enums.Category;

import java.util.Map;

public interface OrderRepository {
    Order getOrder();
    Map<Category,Asset> getOrderCart();
    Order createOrder();
    Asset addAssetToCart(Asset asset);
}
