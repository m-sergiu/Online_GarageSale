package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Order;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.Category;
import com.garagesale.exceptions.CardNotAvailableException;
import com.garagesale.exceptions.OrderDoesNotExistException;
import com.garagesale.exceptions.ProductDoesntExistException;

import java.util.Map;

public interface OrderService {
    Order getOrder();

    Map<Category, Asset> getOrderCart() throws OrderDoesNotExistException;

    Order createOrder();

    Asset addAssetToCart(Asset asset) throws OrderDoesNotExistException;

    PurchaseReceipt finalizeOrder(OrderDTO orderDTO) throws CardNotAvailableException, ProductDoesntExistException, OrderDoesNotExistException;
}
