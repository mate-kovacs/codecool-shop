package com.codecool.shop.utils;

import com.codecool.shop.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class Session {
    private HttpSession session;

    public User getSessionUser(HttpServletRequest request) {
        session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("UserObject", new User());
        }
        return (User)session.getAttribute("UserObj");
    }
}
