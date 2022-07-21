package com.garagesale.repository;

import com.garagesale.domain.Asset;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.User;

import java.util.List;

public interface OrderRepository {
    List<Asset> getAll();
    String createOrder();
    String addAssetToCart();
    CreditCard getCreditCard();
    User getUser();
}
