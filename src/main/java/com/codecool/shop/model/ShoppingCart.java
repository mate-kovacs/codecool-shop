package com.codecool.shop.model;

import com.codecool.shop.dao.implementation.ProductDaoDB;
import com.codecool.shop.dao.implementation.ProductDaoMem;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {
    private HashMap<Product, Integer> shoppingCartContent = new HashMap<Product, Integer>();

    public void addItem(int id) {
        Product productToAdd = ProductDaoDB.getInstance().find(id);
        Boolean isThisItemInTheShoppingCart = false;
        for (Product product: shoppingCartContent.keySet()) {
            if (product.getId() == productToAdd.getId()) {
                shoppingCartContent.put(product, shoppingCartContent.get(product) + 1);
                isThisItemInTheShoppingCart = true;
                break;
            }
        }
        if (!isThisItemInTheShoppingCart) {
            shoppingCartContent.put(productToAdd, 1);
        }
    }

    public void removeItem(int id) {
        Product productToRemove = ProductDaoDB.getInstance().find(id);
        for (Product product: shoppingCartContent.keySet()) {
            if (product.getId() == productToRemove.getId()) {
                Integer currentQuantity = shoppingCartContent.get(product);
                if (currentQuantity > 1) {
                    shoppingCartContent.put(product, --currentQuantity);
                } else if (currentQuantity == 1) {
                    shoppingCartContent.remove(product);
                }
                break;
            }
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
        for (Product product : shoppingCartContent.keySet()) {
            if (product.getId() == productToCount.getId()) {
                return shoppingCartContent.get(product);
            }
        }
        return 0;
    }

    public int getNumberOfItems() {
        return shoppingCartContent.values().stream().mapToInt(i -> i).sum();
    }

    public void clear() {
        shoppingCartContent.clear();
    }
}


