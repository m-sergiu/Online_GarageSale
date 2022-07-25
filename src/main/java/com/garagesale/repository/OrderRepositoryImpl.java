package com.garagesale.repository;

import com.garagesale.domain.Asset;
import com.garagesale.domain.CreditCard;
import com.garagesale.domain.Order;
import com.garagesale.domain.User;
import com.garagesale.enums.Category;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    Order order;

    @Override
    public Order getOrder() {
        return order;
    }

    @Override
    public Map<Category,Asset> getOrderCart() {
        return order.getPurchaseCart();
    }

    @Override
    public Order createOrder() {
        order = new Order();
        order.setId(1);
        order.setPurchaseCart(new HashMap<>());
        return order;
    }

    @Override
    public String addAssetToCart() {
        Asset keyboard = new Asset();
        keyboard.setAssetName("Keyboard DellKm7321");
        keyboard.setId(10);
        keyboard.setCategory(Category.KEYBOARD);
        keyboard.setQuantity(1);
        keyboard.setPrice(45.0d);
        keyboard.setIssues(new String[]{"Alt key is not working"});
        order.addAssetToOrderCart(keyboard);

        Asset asset = new Asset();
        asset.setAssetName("Asset");
        asset.setId(11);
        asset.setCategory(Category.HEADPHONES);
        asset.setQuantity(1);
        asset.setPrice(10.0);
        asset.setIssues(new String[]{" - "});
        order.addAssetToOrderCart(asset);
        return keyboard.getAssetName() + ", " + asset.getAssetName() + " added to the cart ";
    }
    @Override
    public User getUser(){
        User user = new User("Sergiu","Sergiu.muntean@endava.com");
        return user;
    }
    @Override
    public CreditCard getCreditCard(){
        CreditCard creditCard = new CreditCard("1234567890123456",10000,"Sergiu Muntean", "123", LocalDate.of(2030,1,8));
        return creditCard;
    }
}
