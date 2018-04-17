package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductDaoMem;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private HashMap<Product, Integer> shoppingCartContent = new HashMap<Product, Integer>();

    public void addItem(int id) {
        Product productToAdd = ProductDaoMem.getInstance().find(id);
        int quantity = (shoppingCartContent.get(productToAdd) == null)? 1 : shoppingCartContent.get(productToAdd)+1;
        shoppingCartContent.put(productToAdd, quantity);
    }

    public void removeItem(int id) {
        Product productToRemove = ProductDaoMem.getInstance().find(id);
        Integer currentQuantity = shoppingCartContent.get(productToRemove);
        if (currentQuantity > 1) {
            shoppingCartContent.put(productToRemove, --currentQuantity);
        } else if (currentQuantity == 1) {
            shoppingCartContent.remove(productToRemove);
        }
    }

    public HashMap getContent() {
        return shoppingCartContent;
    }

    public float sumCart(){
        float sum = 0;
        for (Map.Entry<Product, Integer> entry : shoppingCartContent.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            sum += product.getDefaultPrice() * quantity;
        }
        return sum;
    }
}


