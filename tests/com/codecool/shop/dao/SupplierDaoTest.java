package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoDB;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {

    @Test
    void add() {
    }

    @Test
    void find() {
        SupplierDaoDB supplierDaoDB = new SupplierDaoDB("test_resources/connection.properties");
        assertTrue(supplierDaoDB.find(1) instanceof Supplier);
    }

    @Test
    void remove() {
    }

    @Test
    void findIdByName() {
    }

    @Test
    void getDefaultSupplier() {
    }

    @Test
    void filterProducts() {
    }

    @Test
    void getAll() {
    }
}