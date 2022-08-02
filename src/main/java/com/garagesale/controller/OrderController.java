package com.garagesale.controller;

import com.garagesale.domain.Asset;
import com.garagesale.domain.Order;
import com.garagesale.domain.PurchaseReceipt;
import com.garagesale.dto.OrderDTO;
import com.garagesale.enums.Category;
import com.garagesale.exceptions.CardNotAvailable;
import com.garagesale.exceptions.NoOrderExistException;
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
    public Map<Category, Asset> getOrderCart() throws NoOrderExistException {
        try {
            return orderService.getOrderCart();
        } catch(NoOrderExistException e){
            System.out.println(e);
            createOrder();
        }
        return orderService.getOrderCart();
    }

//    @PostMapping("/addAssetToCart")
//    public Asset addAssetToPurchaseCart(@RequestBody Asset asset) throws NoOrderExistException {
//        try {
//            return orderService.addAssetToCart(asset);
//        } catch(NoOrderExistException e){
//            createOrder();
//        }
//        return orderService.addAssetToCart(asset);
//    }

    @PostMapping("/pay")
    public PurchaseReceipt finalizeOrder(@RequestBody OrderDTO orderDTO) throws CardNotAvailable, ProductDoesntExist, NoOrderExistException {
        try {
            return orderService.finalizeOrder(orderDTO);
        } catch(NoOrderExistException e){
            System.out.println(e);
            createOrder();
        }
        return orderService.finalizeOrder(orderDTO);
    }

}
