package com.garagesale.domain;

import java.util.HashMap;

public class User {
    private String username;
    private HashMap<Category, Asset> shoppingCart;

    public User(String username) {
        this.username = username;
        this.shoppingCart = new HashMap();
    }

    public User(HashMap<Category, Asset> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public HashMap<Category, Asset> getShoppingCart() {
        return this.shoppingCart;
    }

    public void setShoppingCart(HashMap<Category, Asset> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public static void addAssetToCart(User user, Asset asset) throws ProductAlreadyInCartException {
        if (asset.getQuantity() > 0) {
            if (user.shoppingCart.containsKey(asset.getCategory())) {
                throw new ProductAlreadyInCartException("You already have a " + asset.getCategory());
            }

            user.getShoppingCart().put(asset.getCategory(), asset);
            System.out.println(asset.getAssetName() + " succesfully added to your cart");
            asset.setQuantity(asset.getQuantity() - 1);
        }

    }

    public static void removeAssetFromCart(User user, Asset asset) {
        user.shoppingCart.remove(asset.getCategory());
        asset.setQuantity(asset.getQuantity() + 1);
    }
}
