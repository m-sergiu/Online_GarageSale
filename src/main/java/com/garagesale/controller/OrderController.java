package com.garagesale.controller;

import com.garagesale.domain.Asset;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.exceptions.CreditCardNotAvailable;
import com.garagesale.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping("/getAll")
    public List<Asset> getAllAssetsInCart() {
        return orderService.getAll();
    }

    @RequestMapping("/createOrder")
    public String createOrder() {
        return orderService.createOrder();
    }

    @RequestMapping("/addAssetToCart")
    public String addAssetToPurchaseCart() {
        return orderService.addAssetToCart();
    }

    @RequestMapping("/finalizePurchase")
    public PurchaseReceipt finalizePurchase() throws CreditCardNotAvailable {
        return orderService.finalizePurchase();
    }
}
