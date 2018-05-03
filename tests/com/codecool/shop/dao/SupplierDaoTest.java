package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.SupplierDaoDB;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {
    private SupplierDaoDB supplierDaoDB;

    @BeforeEach
    public void init() {
        supplierDaoDB = new SupplierDaoDB("test_resources/connection.properties");
    }

    @Test
    void testAdd() {
        Supplier testSupplier = new Supplier("TestName", "TestDescription");
        supplierDaoDB.add(testSupplier);
        Supplier addedTestSupplier = supplierDaoDB.find(supplierDaoDB.findIdByName("TestName"));
        testSupplier.setId(addedTestSupplier.getId());
        assertEquals(testSupplier.toString(), addedTestSupplier.toString());
        supplierDaoDB.remove(supplierDaoDB.findIdByName("TestName"));
    }

    @Test
    public void testThrowExceptionWhenAddParameterIsNull() {
        assertThrows(IllegalArgumentException.class, () -> supplierDaoDB.add(null));
    }

    @Test
    void testFind() {
        assertTrue(supplierDaoDB.find(1) instanceof Supplier);
    }

    @Test
    public void testFind_ReturnNull_When_TheIdIsNotInTheDB() {
        assertNull(supplierDaoDB.find(-1));
    }

    @Test
    public void testFind_ReturnNull_When_GiveIntegerMaxValue() {
        assertNull(supplierDaoDB.find(Integer.MAX_VALUE));
    }

    @Test
    void testRemove() {
        Supplier testSupplier = new Supplier("TestRemove", "TestDescription");
        supplierDaoDB.add(testSupplier);
        supplierDaoDB.remove(supplierDaoDB.findIdByName("TestRemove"));
        assertNull(supplierDaoDB.findIdByName("TestRemove"));
    }

    @Test
    void testFindIdByName() {
        assertEquals(2, (int) supplierDaoDB.findIdByName("Füvészkert"));
    }

    @Test
    public void testGivenEmptyStringToFindByName() {
        Supplier testSupplier = new Supplier("", "testDesc");
        supplierDaoDB.add(testSupplier);
        Supplier addedTestSupplier = supplierDaoDB.find(supplierDaoDB.findIdByName(""));
        testSupplier.setId(addedTestSupplier.getId());
        assertEquals(testSupplier.toString(), addedTestSupplier.toString());
        supplierDaoDB.remove(supplierDaoDB.findIdByName(""));
    }

    @Test
    void testGetDefaultSupplier() {
        Supplier testDefaultSupplier = new Supplier("All", "");
        assertEquals(testDefaultSupplier.toString(), supplierDaoDB.getDefaultSupplier().toString());
    }

    @Test
    void filterProducts() {
        List<Product> productList = new ArrayList<>();
        ProductCategory testCategory = new ProductCategory("TestCategory", "test", "test");
        Supplier testDefaultSupplier = new Supplier("All", "");
        Supplier testSupplier = new Supplier("TestName", "TestDescription");
        productList.add(new Product("TestProduct1", 1, "HUF", "test", testCategory, testDefaultSupplier));
        productList.add(new Product("TestProduct2", 2, "HUF", "test", testCategory, testSupplier));
        productList.add(new Product("TestProduct3", 3, "HUF", "test", testCategory, testSupplier));
        productList.add(new Product("TestProduct4", 4, "HUF", "test", testCategory, testDefaultSupplier));
        List<Product> filteredProductList = new ArrayList<>();
        filteredProductList.add(productList.get(1));
        filteredProductList.add(productList.get(2));
        assertEquals(filteredProductList, supplierDaoDB.filterProducts(productList, testSupplier));
    }

    @Test
    void testGetAll() {
        List<Supplier> supplierList = supplierDaoDB.getAll();
        assertEquals(3, supplierList.size());
    }
}