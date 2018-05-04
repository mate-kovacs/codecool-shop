package com.codecool.shop.dao;

import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.util.List;

public interface SupplierDao {

    void add(Supplier supplier);
    Supplier find(int id);
    void remove(int id);
    void removeAll();
    Integer findIdByName(String name);
    Supplier getDefaultSupplier();
    List<Product> filterProducts(List<Product> products, Supplier supplier);

    List<Supplier> getAll();
}
