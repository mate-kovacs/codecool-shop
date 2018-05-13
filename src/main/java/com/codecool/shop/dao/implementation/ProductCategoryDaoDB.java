package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.logging.Level;
import java.util.logging.Logger;


public class ProductCategoryDaoDB implements ProductCategoryDao, Queryhandler {

    private static final Logger logger = Logger.getLogger(ProductCategoryDaoDB.class.getName());

    private String connectionConfigPath = "src/main/resources/connection.properties";
    private static ProductCategoryDaoDB instance = null;

    public static ProductCategoryDaoDB getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoDB();
        }
        return instance;
    }

    public ProductCategoryDaoDB(String connectionConfigPath) {
        this.connectionConfigPath = connectionConfigPath;
    }

    public ProductCategoryDaoDB() {
    }

    @Override
    public void add(ProductCategory category) {
        if (category == null) {
            RuntimeException ex = new IllegalArgumentException("Null category can not be added.");
            logger.log(Level.WARNING, "Category is null.", ex);
            throw ex;
        } else if ("".equals(category.getName())) {
            RuntimeException ex = new ValueException("Category must have a name.");
            logger.log(Level.WARNING, "No name given.", ex);
            throw ex;
        } else if ("".equals(category.getDepartment())) {
            RuntimeException ex = new ValueException("Category must have a department.");
            logger.log(Level.WARNING, "No department given.", ex);
            throw ex;
        } else if ("".equals(category.getDescription())) {
            RuntimeException ex = new ValueException("Category must have a description.");
            logger.log(Level.WARNING, "No description given.", ex);
            throw ex;
        }

        String query = "INSERT INTO product_categories (name, description, department) VALUES" +
                " (?, ?, ?);";
        List<Object> parameters = new ArrayList<>();
        parameters.add(category.getName());
        parameters.add(category.getDescription());
        parameters.add(category.getDepartment());
        logger.log(Level.INFO, "SQL Query to be executed: {0}\nWith parameters: {1}",
                new Object[]{query, parameters});
        executeDMLQuery(query, parameters);
    }

    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM product_categories WHERE id=?;";
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        logger.log(Level.INFO, "SQL Query to be executed: {0}\nWith parameters: {1}",
                new Object[]{query, parameters});
        List<Map<String, Object>> resultList = executeSelectQuery(query, parameters);

        ProductCategory result = null;

        if (resultList.size() == 1) {
            for (Map<String, Object> resultSet : resultList) {
                String name = resultSet.get("name").toString();
                String description = resultSet.get("description").toString();
                String department = resultSet.get("department").toString();
                result = new ProductCategory(name, department, description);
                result.setId(id);
                logger.log(Level.INFO, "Product Category was found: {0}", result);
            }
        }

        return result;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM product_categories WHERE id=?;";
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);

        String tempQuery = "SELECT * FROM product_categories WHERE id=?;";
        List<Map<String, Object>> resultList = executeSelectQuery(tempQuery, parameters);
        if (resultList.size() == 0) {
            logger.log(Level.WARNING, "There is no product category with {0} id in the database.", id);
            throw new IllegalArgumentException("There is no product category with such id in the database.");
        }

        logger.log(Level.INFO, "SQL Query to be executed: {0}\nWith parameters: {1}",
                new Object[]{query, parameters});
        Integer result = executeDMLQuery(query, parameters);

    }

    @Override
    public void removeAll() {
        String query = "DELETE from product_categories;";
        executeDMLQuery(query);
        logger.log(Level.INFO, "All Product Categories removed.");
    }

    @Override
    public Integer findIdByName(String name) {
        String query = "SELECT * FROM product_categories WHERE name=?;";
        List<Object> parameters = new ArrayList<>();
        parameters.add(name);
        logger.log(Level.INFO, "SQL Query to be executed: {0}\nWith parameters: {1}",
                new Object[]{query, parameters});
        List<Map<String, Object>> resultList = executeSelectQuery(query, parameters);

        Integer result = null;
        try {
            result = Integer.parseInt(resultList.get(0).get("id").toString());
        } catch (IndexOutOfBoundsException ex) {
            logger.log(Level.WARNING, "{0}", ex.getMessage());
        }
        return result;
    }

    @Override
    public ProductCategory getDefaultCategory() {
        return new ProductCategory("All", "", "");
    }

    @Override
    public List<Product> filterProducts(List<Product> products, ProductCategory category) {
        if ((category.toString()).equals(getDefaultCategory().toString())) {
            logger.log(Level.INFO, "Product list filtered for 'All' Product Categories.");
            return products;
        }
        List<Product> temp = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductCategory().toString().equals(category.toString())) {
                temp.add(product);
            }
        }
        logger.log(Level.INFO, "Product list filtered for {0} Product Category.", category);
        return temp;
    }

    @Override
    public List<ProductCategory> getAll() {
        String query = "SELECT * FROM product_categories;";
        logger.log(Level.INFO, "SQL Query to be executed: {0}", query);
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
        logger.log(Level.INFO, "Product Categories were found: {0}", results);
        return results;
    }

    @Override
    public String getConnectionConfigPath() {
        return connectionConfigPath;
    }
}
