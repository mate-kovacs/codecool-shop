package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductDaoDB;
import com.codecool.shop.dao.implementation.ProductDaoMem;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private HashMap<Product, Integer> shoppingCartContent = new HashMap<Product, Integer>();

    public void addItem(int id) {
        Product productToAdd = ProductDaoDB.getInstance().find(id);
        Product sameProduct = getSameProductFromShoppingCartContent(productToAdd);
        if (sameProduct == null) shoppingCartContent.put(productToAdd, 1);
        else shoppingCartContent.put(sameProduct, shoppingCartContent.get(sameProduct) + 1);

    }

    public void removeItem(int id) {
        Product productToRemove = ProductDaoDB.getInstance().find(id);
        Product sameProduct = getSameProductFromShoppingCartContent(productToRemove);
        Integer currentQuantity = shoppingCartContent.get(sameProduct);
        if (currentQuantity > 1) shoppingCartContent.put(sameProduct, --currentQuantity);
        else if (currentQuantity == 1) shoppingCartContent.remove(sameProduct);
    }

    public HashMap getContent() {
        return shoppingCartContent;
    }

    public int sumCart() {
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
        Product sameProduct = getSameProductFromShoppingCartContent(productToCount);
        if (sameProduct == null) return 0;
        return shoppingCartContent.get(sameProduct);
    }

    private Product getSameProductFromShoppingCartContent(Product searchedProduct) {
        for (Product product : shoppingCartContent.keySet()) {
            if (product.getId() == searchedProduct.getId()) return product;
        }
        return null;
    }

    public int getNumberOfItems() {
        return shoppingCartContent.values().stream().mapToInt(i -> i).sum();
    }

    public void clear() {
        shoppingCartContent.clear();
    }
}
