package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductCategoryDaoDB implements ProductCategoryDao, Queryhandler {

    private static final String connectionConfigPath = "src/main/resources/connection.properties";

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
        List<Map<String, Object>> resultList = executeSelectQuery(query, parameters);

        ProductCategory result = null;

        for (Map<String, Object> resultSet : resultList) {
            String name = resultSet.get("name").toString();
            String description = resultSet.get("description").toString();
            String department = resultSet.get("department").toString();
            result = new ProductCategory(name, department, description);
            result.setId(id);
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
        List<Map<String, Object>> resultList = executeSelectQuery(query, parameters);

        List<Integer> results = new ArrayList<>();
        for (Map<String, Object> resultSet : resultList) {
            String id = resultSet.get("id").toString();
            results.add(Integer.parseInt(id));
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
        if (category.equals(getDefaultCategory())) {
            return products;
        }
        List<Product> temp = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductCategory().equals(category)) {
                temp.add(product);
            }
        }
        return temp;
    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * FROM product_categories;";
        List<Map<String, Object>> resultList = executeSelectQuery(query);

        List<ProductCategory> results = new ArrayList<>();

        for (Map<String, Object> resultSet : resultList) {
            String id = resultSet.get("id").toString();
            String name = resultSet.get("name").toString();
            String description = resultSet.get("description").toString();
            String department = resultSet.get("department").toString();
            ProductCategory temp = new ProductCategory(name, department, description);
            temp.setId(Integer.parseInt(id));
            results.add(temp);
        }

        return results;
    }

    @Override
    public String getConnectionConfigPath() {
        return connectionConfigPath;
    }
}
