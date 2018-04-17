package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.*;

import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());


        SupplierDao supplierDataStroe = SupplierDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();


        ProductCategory category = productCategoryDataStore.find(productCategoryDataStore.findIdByName(req.getParameter("select_category")));
        Supplier supplier = supplierDataStroe.find(supplierDataStroe.findIdByName(req.getParameter("select_supplier")));

        List<Product> products = filterProducts(supplier, filterProducts(category, productDataStore.getAll() ));

//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        context.setVariable("recipient", "World");
        context.setVariable("category_list", productCategoryDataStore.getAll());
        context.setVariable("supplier_list", supplierDataStroe.getAll());
        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("supplier", supplierDataStroe.find(1));
        context.setVariable("products", products);

        engine.process("product/index.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private List<Product> filterProducts(ProductCategory category, List<Product> products) {
        List<Product> temp = new ArrayList<>();
        for (Product product : products){
            if (product.getProductCategory().equals(category)){
                temp.add(product);
            }
        }
        return temp;
    }

    private List<Product> filterProducts(Supplier supplier, List<Product> products) {
        List<Product> temp = new ArrayList<>();
        for (Product product : products){
            if (product.getSupplier().equals(supplier)){
                temp.add(product);
            }
        }
        return temp;
    }

}
