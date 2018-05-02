package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import java.util.List;

public class SupplierDaoDB implements SupplierDao, Queryhandler{
    @Override
    public void add(Supplier supplier) {

    }

    @Override
    public Supplier find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public Integer findIdByName(String name) {
        return null;
    }

    @Override
    public Supplier getDefaultSupplier() {
        return null;
    }

    @Override
    public List<Product> filterProducts(List<Product> products, Supplier supplier) {
        return null;
    }

    @Override
    public List<Supplier> getAll() {
        return null;
    }

    @Override
    public String getConnectionConfigPath() {
        return null;
    }

    @Override
    public void setConnectionConfigPath() {

    }
}
