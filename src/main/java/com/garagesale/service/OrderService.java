package com.garagesale.service;

import com.garagesale.domain.*;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.Category;
import com.garagesale.exceptions.CardNotAvailable;
import com.garagesale.exceptions.ProductDoesntExist;

import java.util.Map;

public interface OrderService {
     Order getOrder();

     Map<Category,Asset> getOrderCart();
     Order createOrder();
     Asset addAssetToCart(Asset asset);
     PurchaseReceipt finalizeOrder(OrderDTO orderDTO) throws CardNotAvailable, ProductDoesntExist;
}
