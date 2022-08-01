package com.garagesale.controller;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Order;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.Category;
import com.garagesale.exceptions.CardNotAvailable;
import com.garagesale.exceptions.ProductDoesntExist;
import com.garagesale.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public Order getOrder() {
        return orderService.getOrder();
    }
    @GetMapping("/create")
    public Order createOrder() {
        return orderService.createOrder();
    }

    @GetMapping("/getCart")
    public Map<Category, Asset> getOrderCart() {
        try {
            return orderService.getOrderCart();
        } catch(NullPointerException e){
            createOrder();
        }
        return orderService.getOrderCart();
    }

//    @PostMapping("/addAssetToCart")
//    public Asset addAssetToPurchaseCart(@RequestBody Asset asset) {
//        try {
//            return orderService.addAssetToCart(asset);
//        } catch(NullPointerException e){
//            createOrder();
//        }
//        return orderService.addAssetToCart(asset);
//    }

    @PostMapping("/pay")
    public PurchaseReceipt finalizeOrder(@RequestBody OrderDTO orderDTO) throws CardNotAvailable, ProductDoesntExist {
        try {
            return orderService.finalizeOrder(orderDTO);
        } catch(NullPointerException e){
            createOrder();
        }
        return orderService.finalizeOrder(orderDTO);
    }

}
