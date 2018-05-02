package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SupplierDaoDB implements SupplierDao, Queryhandler{
    private final String CONNECTION_CONFIG_PATH = "src/main/resources/connection.properties";

    @Override
    public void add(Supplier supplier) {
        String query = "INSERT INTO suppliers (name, description) VALUES (?, ?)";
        List<Object> parameters = Stream.of(supplier.getName(), supplier.getDescription()).collect(Collectors.toList());
        executeDMLQuery(query, parameters);
    }

    @Override
    public Supplier find(int id) {
        String query = "SELECT * FROM suppliers WHERE id = ?";
        List<Object> parameters = Stream.of(id).collect(Collectors.toList());
        ResultSet result = executeSelectQuery(query, parameters);
        Supplier supplier = null;
        try {
            supplier = new Supplier(result.getString("name"), result.getString("description"));
            supplier.setId(result.getInt("id"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return supplier;
    }

    @Override
    public void remove(int id) {
        String query = "DELETE FROM suppliers WHERE id = ?";
        List<Object> parameters = Stream.of(id).collect(Collectors.toList());
        executeSelectQuery(query, parameters);
    }

    @Override
    public Integer findIdByName(String name) {
        String query = "SELECT * FROM suppliers WHERE name = ?";
        List<Object> parameters = Stream.of(name).collect(Collectors.toList());
        ResultSet result = executeSelectQuery(query, parameters);
        Integer id = null;
        try {
            id = result.getInt("id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public Supplier getDefaultSupplier() {
        Supplier defaultSupplier = new Supplier("All", "");
        return defaultSupplier;
    }

    @Override
    public List<Product> filterProducts(List<Product> products, Supplier supplier) {
        if (supplier.equals(getDefaultSupplier())) {
            return products;
        }
        List<Product> temp = new ArrayList<>();
        for (Product product : products) {
            if (product.getSupplier().equals(supplier)) {
                temp.add(product);
            }
        }
        return temp;
    }

    @Override
    public List<Supplier> getAll() {
        String query = "SELECT * FROM suppliers;";
        List<Supplier> allSupplierList = new ArrayList<>();
        try {
            ResultSet result = executeSelectQuery(query);
            while (result.next()) {
                Supplier supplier = new Supplier(result.getString("name"), result.getString("description"));
                supplier.setId(result.getInt("id"));
                allSupplierList.add(supplier);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return allSupplierList;
    }

    @Override
    public String getConnectionConfigPath() {
        return CONNECTION_CONFIG_PATH;
    }
}
