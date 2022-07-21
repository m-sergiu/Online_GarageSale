package com.garagesale.service;

import com.garagesale.domain.Asset;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.domain.User;
import com.garagesale.exceptions.CreditCardNotAvailable;

import java.util.List;

public interface OrderService {

     List<Asset> getAll();
     String createOrder();
     String addAssetToCart();
     PurchaseReceipt finalizePurchase() throws CreditCardNotAvailable;
     User getUser();
     CreditCard getCreditCard();
}
