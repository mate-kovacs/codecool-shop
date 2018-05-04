package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoDB;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {

    private static String testProperties = "test_resources/connection.properties";
    private static ProductCategoryDao testDao;

    @BeforeEach
    void setup() {
        testDao = new ProductCategoryDaoDB(testProperties);
    }

    @Test
    void test_find_id_0() {
        assertEquals(null, testDao.find(0));
    }

    @Test
    void test_find_id_1() {
        assertEquals("animal", testDao.find(1).getName());
    }

    @Test
    void test_find_id_negative() {
        assertEquals(null, testDao.find(-7));
    }

    @Test
    void test_find_id_too_big() {
        assertEquals(null, testDao.find(Integer.MAX_VALUE));
    }

    @Test
    void test_add_null() {
        assertThrows(IllegalArgumentException.class, () -> testDao.add(null));
    }

    @Test
    void test_add_no_name() {
        ProductCategory category = new ProductCategory("", "foo", "bar");
        assertThrows(ValueException.class, () -> testDao.add(category));
    }

    @Test
    void test_add_no_department() {
        ProductCategory category = new ProductCategory("foo", "", "bar");
        assertThrows(ValueException.class, () -> testDao.add(category));
    }

    @Test
    void test_add_no_description() {
        ProductCategory category = new ProductCategory("foo", "bar", "");
        assertThrows(ValueException.class, () -> testDao.add(category));
    }

    @Test
    void test_add_valid() {
        ProductCategory category = new ProductCategory("mushroom",
                "shroom depatrment",
                "Not an animal, but not a palnt either. Something in between.");
        testDao.add(category);
        assertEquals("mushroom", testDao.find(testDao.findIdByName("mushroom")).getName());
    }

    @Test
    void test_remove_id_0() {
        assertThrows(IllegalArgumentException.class, () -> testDao.remove(0));
    }

    @Test
    void test_remove_id_negative() {
        assertThrows(IllegalArgumentException.class, () -> testDao.remove(-7));
    }

    @Test
    void test_remove_id_too_big() {
        assertThrows(IllegalArgumentException.class, () -> testDao.remove(Integer.MAX_VALUE));
    }

    @Test
    void test_remove_with_foreign_key() {
        testDao.remove(2);
        assertEquals(null, testDao.find(2));
    }

    @Test
    void test_remove_no_foreign_key() {
        ProductCategory category = new ProductCategory("bacteria",
                "bacta tank",
                "One cell organizm,");
        testDao.add(category);
        testDao.remove(testDao.findIdByName("bacteria"));
        assertEquals(null, testDao.findIdByName("bacteria"));
    }

    @Test
    void test_findIdByName_null() {
        assertEquals(null, testDao.findIdByName(null));
    }

    @Test
    void test_findIdByName_no_name() {
        assertEquals(null, testDao.findIdByName(""));
    }

    @Test
    void test_findIdByName_valid() {
        assertEquals(1, Integer.parseInt(testDao.findIdByName("animal").toString()));
    }

    @Test
    void getDefaultCategory() {
        assertEquals("All", testDao.getDefaultCategory().getName());
    }

    @Test
    void getAll() {
    }
}