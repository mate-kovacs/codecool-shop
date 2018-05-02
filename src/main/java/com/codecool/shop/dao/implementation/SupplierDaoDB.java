package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.Supplier;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SupplierDaoDB implements SupplierDao, Queryhandler {
    private String connection_config_path = "src/main/resources/connection.properties";

    public SupplierDaoDB(String connection_config_path) {
        this.connection_config_path = connection_config_path;
    }

    public SupplierDaoDB() {
    }

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
        List<Map<String, Object>> result = executeSelectQuery(query, parameters);
        Supplier supplier = new Supplier((String) result.get(0).get("name"), (String) result.get(0).get("description"));
        supplier.setId((Integer) result.get(0).get("id"));
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
        List<Map<String, Object>> result = executeSelectQuery(query, parameters);
        Integer id = (Integer) result.get(0).get("id");
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
        List<Map<String, Object>> result = executeSelectQuery(query);
        for (Map<String, Object> row : result) {
            Supplier supplier = new Supplier((String) row.get("name"), (String) row.get("description"));
            supplier.setId((Integer) result.get(0).get("id"));
            allSupplierList.add(supplier);
        }
        return allSupplierList;
    }

    @Override
    public String getConnectionConfigPath() {
        return connection_config_path;
    }
}
