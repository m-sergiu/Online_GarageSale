package com.garagesale.repository;

import com.garagesale.domain.Asset;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.Order;
import com.garagesale.domain.User;
import com.garagesale.enums.Category;

import java.util.List;
import java.util.Map;

public interface OrderRepository {
    Order getOrder();
    Map<Category,Asset> getOrderCart();
    Order createOrder();
    String addAssetToCart();
    CreditCard getCreditCard();
    User getUser();
}
