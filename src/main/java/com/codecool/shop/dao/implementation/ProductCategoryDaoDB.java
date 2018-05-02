package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;

public class ProductCategoryDaoDB implements ProductCategoryDao, Queryhandler {
    @Override
    public void add(ProductCategory category) {
        String query = "ISNERT INTO product_categories (name, description, department) VALUES" +
                " (?, ?, ?);";
        List<Object> parameters = new ArrayList<>();
        parameters.add(category.getName());
        parameters.add(category.getDescription());
        parameters.add(category.getDepartment());
        executeDMLQuery(query, parameters);
    }

    @Override
    public ProductCategory find(int id) {
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
    public ProductCategory getDefaultCategory() {
        return null;
    }

    @Override
    public List<Product> filterProducts(List<Product> products, ProductCategory category) {
        return null;
    }

    @Override
    public List<ProductCategory> getAll() {
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
