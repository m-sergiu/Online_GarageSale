package com.garagesale.controller;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Order;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.enums.Category;
import com.garagesale.exceptions.CreditCardNotAvailable;
import com.garagesale.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping
    public Order getOrder() {
        return orderService.getOrder();
    }

    @RequestMapping("/getOrderCart")
    public Map<Category, Asset> getOrderCart() {
        return orderService.getOrderCart();
    }

    @RequestMapping("/createOrder")
    public Order createOrder() {
        return orderService.createOrder();
    }

    @RequestMapping("/addAssetToCart")
    public String addAssetToPurchaseCart() {
        return orderService.addAssetToCart();
    }

    @RequestMapping("/finalizeOrder")
    public PurchaseReceipt finalizeOrder() throws CreditCardNotAvailable {
        return orderService.finalizeOrder();
    }
}
