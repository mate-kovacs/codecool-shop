package com.codecool.shop.model;

import java.util.List;

public class User {
    List<Order> orders;
    ShoppingCart shoppingCart;

    User () {
        shoppingCart = new ShoppingCart();
    }
}
