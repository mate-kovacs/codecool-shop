package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductDaoDB;
import com.codecool.shop.dao.implementation.ProductDaoMem;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private HashMap<Product, Integer> shoppingCartContent = new HashMap<Product, Integer>();

    public void addItem(int id) {
        Product productToAdd = ProductDaoDB.getInstance().find(id);
        int quantity = (shoppingCartContent.get(productToAdd) == null)? 1 : shoppingCartContent.get(productToAdd)+1;
        shoppingCartContent.put(productToAdd, quantity);
    }

    public void removeItem(int id) {
        Product productToRemove = ProductDaoDB.getInstance().find(id);
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

    public int sumCart(){
        int sum = 0;
        for (Map.Entry<Product, Integer> entry : shoppingCartContent.entrySet()) {
            Product product = entry.getKey();
            Integer quantity = entry.getValue();
            sum += product.getDefaultPrice() * quantity;
        }
        return sum;
    }

    public int getNumberOfItemById(int id) {
        Product productToCount = ProductDaoDB.getInstance().find(id);
        return (shoppingCartContent.get(productToCount) == null)? 0 : shoppingCartContent.get(productToCount);
    }

    public int getNumberOfItems() {
        return shoppingCartContent.values().stream().mapToInt(i -> i).sum();
    }

    public void clear() {
        shoppingCartContent.clear();
    }
}


