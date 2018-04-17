package com.codecool.shop.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Session {
    private HttpSession session;

    public User getSessionUser(HttpServletRequest request) {
        session = request.getSession();
        if (session.isNew()) {
            session.setAttribute("UserObject", new User());
        }
        return sessionUser = (User)session.getAttribute("UserObj");
    }
}
