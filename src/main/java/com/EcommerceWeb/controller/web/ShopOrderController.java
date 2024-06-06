package com.EcommerceWeb.controller.web;

import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.utils.SessionUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/shop-order"})
public class ShopOrderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedIds = req.getParameter("selectedIds");
        boolean success = true;
        SiteUser siteUser = (SiteUser) SessionUtil.getInstance().getValue(req, "SITEUSER");

        if (siteUser == null || selectedIds == null || selectedIds.isEmpty()) {
            success = false;
        }





        if (success) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/web/checkout.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }
}
