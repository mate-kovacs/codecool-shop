package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.ProductCategoryDaoDB;
import com.codecool.shop.dao.implementation.ProductDaoDB;
import com.codecool.shop.dao.implementation.SupplierDaoDB;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {

    private ProductDaoDB productDaoDB;
    private Product productToAdd;
    private int numberOfProducts;
    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Product> productsBySupplierDani = new ArrayList<>();
    private ArrayList<Product> productsByCategoryMaterial = new ArrayList<>();
    private Supplier mentorDani;
    private ProductCategory material;


    private void initProduct() {
        ProductCategory specialSkills = new ProductCategory("Special skills", "-",
                "Personal training in the mentor's special fields of interest");
        Supplier mentorBence = new Supplier("Bence", "Mouse-less computer user");
        productToAdd = new Product("Guitar Hero", 10, "USD",
                "Bence will accompany you on guitar in a performace for 45 minutes",
                specialSkills, mentorBence);
        numberOfProducts = productDaoDB.numberOfProducts();
    }

    @BeforeEach
    void setup() {
        productDaoDB = new ProductDaoDB("src/main/test_resources/connection_properties");

        ProductCategory material = new ProductCategory("Material", "-", "Written materials in different topics");
        this.material = material;
        ProductCategoryDaoDB productCategoryDaoDB = new ProductCategoryDaoDB();
        productCategoryDaoDB.add(material);

        ProductCategory personalProgramming = new ProductCategory("Personal programming", "-", "Personal live help from mentors");
        ProductCategory specialSkills = new ProductCategory("Special skills", "-", "Personal training in the mentor's special fields of interest");
        ProductCategory entertainment = new ProductCategory("Entertainment", "-", "Fun with mentors");
        Supplier mentorBence = new Supplier("Bence", "Mouse-less computer user");
        Supplier mentorZozi = new Supplier("Zozi", "IT-guy philosopher halfling");
        Supplier mentorRudi = new Supplier("Rudi", "The manifestation of serenity");
        Supplier mentorDani = new Supplier("Dani", "The designer with a chair");

        this.mentorDani = mentorDani;
        SupplierDaoDB supplierDaoDB = new SupplierDaoDB();
        supplierDaoDB.add(mentorDani);

        Product product = new Product("Guitar Hero", 10, "USD", "Bence will accompany you on guitar in a performace for 45 minutes", entertainment, mentorBence);
        Product product1 = new Product("Departure from the Cave", 10, "USD", "Zozi will enlighten you in a random philosophical topic", specialSkills, mentorZozi);
        Product product2 = new Product("Ring the Bell", 10, "USD", "Rudi will be your personal kettle bell trainer for an hour", material, mentorRudi);
        Product product3 = new Product("By Design", 10, "USD", "Dani will brag about one of his creations for an hour", personalProgramming, mentorDani);
        Product product4 = new Product("By Design", 10, "USD", "Dani will brag about one of his creations for an hour", material, mentorDani);

        productDaoDB.add(product);
        productDaoDB.add(product1);
        productDaoDB.add(product2);
        productDaoDB.add(product3);
        productDaoDB.add(product4);

        products.add(product);
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);

        productsBySupplierDani.add(product3);
        productsBySupplierDani.add(product4);

        productsByCategoryMaterial.add(product2);
        productsByCategoryMaterial.add(product4);

        numberOfProducts = productDaoDB.numberOfProducts();
    }

    @AfterEach
    @Test
    void removeAllProducts() {
        productDaoDB.removeAllProducts();
        numberOfProducts = productDaoDB.numberOfProducts();

        assertTrue(numberOfProducts == 0);
    }

    @Test
    void testProductNumberGrowsOnAdd() {
        initProduct();
        int numberOfProducts = productDaoDB.numberOfProducts();
        productDaoDB.add(productToAdd);
        int numberOfProductsAfter = productDaoDB.numberOfProducts();

        assertTrue(numberOfProductsAfter - numberOfProducts == 1);
    }

    @Test
    void testFindProduct() {
        Product productInDB = productDaoDB.find(2);
        Product productInArray = products.get(2);

        assertEquals(productInArray, productInDB);
    }

    @Test
    void testRemoveNumberDecreaseOnRemove() {
        int productId = productToAdd.getId();
        productDaoDB.remove(productId);
        int numberOfProductsAfter = productDaoDB.numberOfProducts();

        assertTrue(numberOfProducts - numberOfProductsAfter == 1);
    }

    @Test
    void testGetAll() {
        assertEquals(products, productDaoDB.getAll());
    }

    @Test
    void testGetBySupplier() {
        assertEquals(productsBySupplierDani, productDaoDB.getBy(mentorDani));
    }

    @Test
    void testGetByProductCategory() {
        assertEquals(productsByCategoryMaterial, productDaoDB.getBy(material));
    }
}