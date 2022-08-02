package com.garagesale.service;

import com.garagesale.domain.*;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.Category;
import com.garagesale.exceptions.CardNotAvailable;
import com.garagesale.exceptions.NoOrderExistException;
import com.garagesale.exceptions.ProductDoesntExist;

import java.util.Map;

public interface OrderService {
     Order getOrder();

     Map<Category,Asset> getOrderCart() throws NoOrderExistException;
     Order createOrder();
     Asset addAssetToCart(Asset asset) throws NoOrderExistException;
     PurchaseReceipt finalizeOrder(OrderDTO orderDTO) throws CardNotAvailable, ProductDoesntExist, NoOrderExistException;
}
