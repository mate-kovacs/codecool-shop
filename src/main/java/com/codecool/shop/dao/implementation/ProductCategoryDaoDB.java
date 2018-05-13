package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Singleton Data Access Object to access Product Categories, stored in database.
 */
public class ProductCategoryDaoDB implements ProductCategoryDao, Queryhandler {

    private String connectionConfigPath = "src/main/resources/connection.properties";
    private static ProductCategoryDaoDB instance = null;

    public static ProductCategoryDaoDB getInstance() {
        if (instance == null) {
            instance = new ProductCategoryDaoDB();
        }
        return instance;
    }

    /**
     * Generates the Singleton Data Access Object for Product Categories.
     * Sets the configuration file to use for building the database connection (to find the correct database).
     *
     * @param connectionConfigPath: string filepath starting from the project root, pointing at the config file for the database to connect to.
     */
    public ProductCategoryDaoDB(String connectionConfigPath) {
        this.connectionConfigPath = connectionConfigPath;
    }

    /**
     * Generates the Singleton Data Access Object for Product Categories.
     * Use the DEFAULT configuration file to use for building the database connection (to find the correct database).
     */
    public ProductCategoryDaoDB() {
    }

    /**
     * Takes a Product Category Object and inserts it into the database as a new record.
     * Product Category must have a name, description and department.
     *
     * @param category: the Product Category Object to be stored in the database.
     */
    @Override
    public void add(ProductCategory category) {
        if (category == null) {
            throw new IllegalArgumentException("Null category can not be added.");
        } else if ("".equals(category.getName())) {
            throw new ValueException("Category must have a name.");
        } else if ("".equals(category.getDepartment())) {
            throw new ValueException("Category must have a department.");
        } else if ("".equals(category.getDescription())) {
            throw new ValueException("Category must have a description.");
        }

        String query = "INSERT INTO product_categories (name, description, department) VALUES" +
                " (?, ?, ?);";
        List<Object> parameters = new ArrayList<>();
        parameters.add(category.getName());
        parameters.add(category.getDescription());
        parameters.add(category.getDepartment());
        executeDMLQuery(query, parameters);
    }

    /**
     * Searches the database table for Product Categories, and returns the one that has a matching id to the argument.
     * If no match is found, returns null.
     *
     * @param id: integer id to search for in the database.
     * @return Product Category Object with matching id if found in the database, null otherwise.
     */
    @Override
    public ProductCategory find(int id) {
        String query = "SELECT * FROM product_categories WHERE id=?;";
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);
        List<Map<String, Object>> resultList = executeSelectQuery(query, parameters);

        ProductCategory result = null;

        if (resultList.size() == 1) {
            for (Map<String, Object> resultSet : resultList) {
                String name = resultSet.get("name").toString();
                String description = resultSet.get("description").toString();
                String department = resultSet.get("department").toString();
                result = new ProductCategory(name, department, description);
                result.setId(id);
            }
        }

        return result;
    }

    /**
     * Removes a Product Category record from the database, if it has a matching id to the argument.
     * The argument must match one of the valid ids in the database.
     *
     * @param id: integer id of the Product Category to search for, and delete from the database.
     */
    @Override
    public void remove(int id) {
        String query = "DELETE FROM product_categories WHERE id=?;";
        List<Object> parameters = new ArrayList<>();
        parameters.add(id);

        String tempQuery = "SELECT * FROM product_categories WHERE id=?;";
        List<Map<String, Object>> resultList = executeSelectQuery(tempQuery, parameters);
        if (resultList.size() == 0) {
            throw new IllegalArgumentException("There is no product category with such id in the database.");
        }

        Integer result = executeDMLQuery(query, parameters);

    }

    /**
     * Removes all Product Categories from the database.
     */
    @Override
    public void removeAll() {
        String query = "DELETE from product_categories;";
        executeDMLQuery(query);
    }

    /**
     * Searches the database for Product Categories with matching names to the argument.
     * The id of the first one's will be returned as an integer.
     * The name must match at least one Product Category in the database.
     *
     * @param name: string name of the Product Category that's id is to be found.
     * @return Integer id of the Product Category with matching name.
     */
    @Override
    public Integer findIdByName(String name) {
        String query = "SELECT * FROM product_categories WHERE name=?;";
        List<Object> parameters = new ArrayList<>();
        parameters.add(name);
        List<Map<String, Object>> resultList = executeSelectQuery(query, parameters);

        Integer result = null;
        try {
            result = Integer.parseInt(resultList.get(0).get("id").toString());
        } catch (IndexOutOfBoundsException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return result;
    }

    /**
     * Retruns the default "All" Product Category.
     *
     * @return Product Category Object with name "All", and no department of description.
     */
    @Override
    public ProductCategory getDefaultCategory() {
        return new ProductCategory("All", "", "");
    }

    /**
     * Takes a list of Product Objects and returns only those, that have a matching Product Category to the argument.
     * For default Product Category it returns the same List. For any other it returns the modified List.
     *
     * @param products: List of Product Objects to be filtered.
     * @param category: Product Category Object used for filtering the List.
     * @return List of Product Objects, filtered by Product Category.
     */
    @Override
    public List<Product> filterProducts(List<Product> products, ProductCategory category) {
        if ((category.toString()).equals(getDefaultCategory().toString())) {
            return products;
        }
        List<Product> temp = new ArrayList<>();
        for (Product product : products) {
            if (product.getProductCategory().toString().equals(category.toString())) {
                temp.add(product);
            }
        }
        return temp;
    }

    /**
     * Takes all Product Categories from the database, and returns them as a List of Product Category Objects.
     * Does not include the Default Product Category.
     *
     * @return List of all Product Category Objects stored in the database.
     */
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

    /**
     * Returns the filepath to the config file, which determines how to build the database connection.
     *
     * @return string filepath starting from the project root, pointing at the config file for the database to connect to.
     */
    @Override
    public String getConnectionConfigPath() {
        return connectionConfigPath;
    }
}
