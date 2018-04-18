package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    public List<Order> orders = new ArrayList<>();
    ShoppingCart shoppingCart;

    public User () {
        shoppingCart = new ShoppingCart();
    }
}
