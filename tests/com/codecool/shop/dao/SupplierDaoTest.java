package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoDB;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {

    @Test
    void add() {
        SupplierDaoDB supplierDaoDB = new SupplierDaoDB("test_resources/connection.properties");
        Supplier testSupplier = new Supplier("TestName", "TestDescription");
        supplierDaoDB.add(testSupplier);
        Supplier addedTestSupplier = supplierDaoDB.find(supplierDaoDB.findIdByName("TestName"));
        testSupplier.setId(addedTestSupplier.getId());
        assertEquals(testSupplier.toString(), addedTestSupplier.toString());
    }

    @Test
    void find() {
        SupplierDaoDB supplierDaoDB = new SupplierDaoDB("test_resources/connection.properties");
        assertTrue(supplierDaoDB.find(1) instanceof Supplier);
    }

    @Test
    void remove() {
        SupplierDaoDB supplierDaoDB = new SupplierDaoDB("test_resources/connection.properties");
        supplierDaoDB.remove(supplierDaoDB.findIdByName("TestName"));
        assertThrows(IndexOutOfBoundsException.class, () -> supplierDaoDB.findIdByName("TestName"));
    }

    @Test
    void findIdByName() {
        SupplierDaoDB supplierDaoDB = new SupplierDaoDB("test_resources/connection.properties");
        assertEquals(2, (int)supplierDaoDB.findIdByName("Füvészkert"));
    }

    @Test
    void getDefaultSupplier() {
    }

    @Test
    void filterProducts() {
    }

    @Test
    void getAll() {
        SupplierDaoDB supplierDaoDB = new SupplierDaoDB("test_resources/connection.properties");
        List<Supplier> supplierList = supplierDaoDB.getAll();
        assertEquals(3, supplierList.size());
    }
}