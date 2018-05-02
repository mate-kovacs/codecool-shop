package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;
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
        String query = "SELECT * FROM product_categories WHERE id=?;";
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        ResultSet resultSet = executeSelctQuery(query, parameters);

        ProductCategory result = null;
        try {
            resultSet.next();
            String name = resultSet.getString("name");
            String description = resultSet.getString("description");
            String department = resultSet.getString("department");
            result = new ProductCategory(name, department, description);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM product_categories WHERE id=?;";
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        executeDMLQuery(query, parameters);
    }

    @Override
    public Integer findIdByName(String name) {
        String query = "SELECT * FROM product_categories WHERE name=?;";
        List<Object> parameters = new ArrayList<>();
        parameters.add(name);
        ResultSet resultSet = executeSelctQuery(query, parameters);

        List<Integer> results = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                results.add(Integer.parseInt(id));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        Integer result = null;
        try {
            result = results.get(0);
        } catch (ArrayIndexOutOfBoundsException | NullPointerException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public ProductCategory getDefaultCategory() {
        return new ProductCategory("All", "", "");
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
