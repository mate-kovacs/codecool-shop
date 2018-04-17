package com.codecool.shop.model;

import java.util.List;

public class User {
    public List<Order> orders;
    public ShoppingCart shoppingCart;

    public User () {
        shoppingCart = new ShoppingCart();
    }
}
