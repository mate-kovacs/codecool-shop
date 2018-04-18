package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.model.ShoppingCart;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet (urlPatterns = "/shoppingcart")
public class ShoppingCartController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("UserObject", new User());
        }
        User user = (User)session.getAttribute("UserObject");

        //TESTING SHIT:
        user.shoppingCart.addItem(2);
        user.shoppingCart.addItem(2);
        user.shoppingCart.addItem(1);
        user.shoppingCart.addItem(3);
        user.shoppingCart.addItem(3);
        user.shoppingCart.addItem(3);
        //TESTING SHIT END

        ShoppingCart shoppingCart = user.shoppingCart;

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

        context.setVariable("shoppingcart", shoppingCart.getContent());

        engine.process("shoppingcart/shoppingcart.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}
