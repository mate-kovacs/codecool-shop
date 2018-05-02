package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoDB implements ProductDao, Queryhandler {

    private final String CONNECTION_CONFIG_PATH = "src/main/resources/connection.properties";

    @Override
    public void add(Product product) {

        String query = "INSERT INTO products (name, description, default_price, default_currency, product_category, supplier)" +
                        "VALUES (?, ?, ?, ?, ?, ?);";

        List<Object> parameters = new ArrayList<>();
        parameters.add(product.getName());
        parameters.add(product.getDescription());
        parameters.add(product.getDefaultPrice());
        parameters.add(product.getDefaultCurrency());
        parameters.add(product.getProductCategory().getId());
        parameters.add(product.getSupplier().getId());

        executeDMLQuery(query, parameters);
    }

    @Override
    public Product find(int id) {
        Product product = null;
        String query = "SELECT id, name, description, default_price, default_currency, product_category, supplier " +
                        "FROM products " +
                        "WHERE id=?;";
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        ResultSet result = executeSelectQuery(query, parameters);
        try
        {
            String name = result.getString("name");
            String description = result.getString("description");
            int defaultPrice = result.getInt("default_price");
            String defaultCurrency = result.getString("default_currency");
            int productCategory = result.getInt("product_category");
            int supplier = result.getInt("supplier");
            ProductCategory productCategory1 = new ProductCategoryDaoDB().find(productCategory);
            Supplier supplier1 = new SupplierDaoDB().find(supplier);
            product = new Product(name, defaultPrice, defaultCurrency, description, productCategory1, supplier1);
            product.setId(id);
        }
        catch (SQLException e) {
            System.out.println("bad thing happened: " + e);
        }
        return product;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public List<Product> getBy(Supplier supplier) {
        return null;
    }

    @Override
    public List<Product> getBy(ProductCategory productCategory) {
        return null;
    }

    @Override
    public String getConnectionConfigPath() {
        return CONNECTION_CONFIG_PATH;
    }

    @Override
    public void setConnectionConfigPath() {

    }
}
