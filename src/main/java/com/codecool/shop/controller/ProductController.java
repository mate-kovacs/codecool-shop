package com.codecool.shop.controller;

import org.json.JSONObject;

import com.codecool.shop.model.User;
import com.codecool.shop.model.ShoppingCart;

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
import javax.servlet.http.HttpSession;
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


        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();


        ProductCategory category;
        Supplier supplier;

        String selectedCategory = req.getParameter("select_category");
        if (selectedCategory != null &&
                !selectedCategory.equals(productCategoryDataStore.getDefaultCategory().getName())) {
            category = productCategoryDataStore.find(
                    productCategoryDataStore.findIdByName(
                            req.getParameter("select_category")));
        } else {
            category = productCategoryDataStore.getDefaultCategory();
        }

        String selectedSupplier = req.getParameter("select_supplier");
        if (selectedSupplier != null &&
                !selectedSupplier.equals(supplierDataStore.getDefaultSupplier().getName())) {
            supplier = supplierDataStore.find(
                    supplierDataStore.findIdByName(
                            req.getParameter("select_supplier")));
        } else {
            supplier = supplierDataStore.getDefaultSupplier();
        }

        List<Product> products = supplierDataStore.filterProducts(
                productCategoryDataStore.filterProducts(
                        productDataStore.getAll(), category), supplier);


        if (req.getParameter("ajax") != null) {
            JSONObject json = new JSONObject();
            int numberOfProducts = 0;
            for (Product product : products) {

                JSONObject temp = new JSONObject();
                temp.put("title", product.getName());
                temp.put("desciption", product.getDescription());
                temp.put("id", product.getId());
                temp.put("price", product.getPrice());

                json.put("product" + numberOfProducts, temp);
                numberOfProducts ++;
            }
            json.put("elements", numberOfProducts);

            resp.setContentType("application/json");
            resp.getWriter().print(json);

        } else {
            context.setVariable("recipient", "World");
            context.setVariable("category_list", productCategoryDataStore.getAll());
            context.setVariable("supplier_list", supplierDataStore.getAll());
            context.setVariable("category", category);
            context.setVariable("supplier", supplier);
            context.setVariable("products", products);

            engine.process("product/index.html", context, resp.getWriter());
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session;
        session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("UserObject", new User());
        }
        User user = (User) session.getAttribute("UserObject");
        String productId = request.getParameter("id");
        ShoppingCart shoppingCart = user.shoppingCart;
        shoppingCart.addItem(Integer.parseInt(productId));

        float priceSum = shoppingCart.sumCart();
        int numberOfItems = shoppingCart.getContent().size();

        JSONObject json = new JSONObject();
        json.put("priceSum", priceSum);
        json.put("numberOfItems", numberOfItems);

        response.setContentType("application/json");
        response.getWriter().print(json);
    }

}
