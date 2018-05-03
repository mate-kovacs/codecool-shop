package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoDB;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.Queryhandler;
import com.codecool.shop.model.ProductCategory;
import jdk.nashorn.internal.runtime.regexp.joni.exception.ValueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.management.InvalidAttributeValueException;

import java.sql.Connection;
import java.sql.Statement;

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
        ProductCategory category = null;
        assertThrows(IllegalArgumentException.class, () -> testDao.add(category));
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
        assertEquals("mushroom", testDao.find(15).getName());
    }

    @Test
    void remove() {
    }

    @Test
    void findIdByName() {
    }

    @Test
    void getDefaultCategory() {
    }

    @Test
    void filterProducts() {
    }

    @Test
    void getAll() {
    }
}