package com.garagesale.service;

import com.garagesale.domain.*;
import com.garagesale.enums.Category;
import com.garagesale.exceptions.CreditCardNotAvailable;

import java.util.List;
import java.util.Map;

public interface OrderService {
     Order getOrder();

     Map<Category,Asset> getOrderCart();
     Order createOrder();
     String addAssetToCart();
     PurchaseReceipt finalizeOrder() throws CreditCardNotAvailable;
     User getUser();
     CreditCard getCreditCard();
}
