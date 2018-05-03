package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoDB;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {

    private static String testProperties = "test_resources/connection.properties";
    private static ProductCategoryDao testDao;

    @BeforeEach
    void setup(){
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
    void add() {

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