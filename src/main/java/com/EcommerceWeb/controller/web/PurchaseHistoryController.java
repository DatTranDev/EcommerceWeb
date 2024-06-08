package com.EcommerceWeb.controller.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



@WebServlet(urlPatterns = {"/purchaseHistory"})
public class PurchaseHistoryController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String result = request.getParameter("idShopOrder").trim();


        int idShopOrder;
        if (result != null && !result.isEmpty()) {
            idShopOrder=Integer.parseInt(result);
            System.out.println(idShopOrder+"");
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/PurchaseHistory.jsp");
            rd.forward(request, response);
        }
        else{
            response.sendRedirect(request.getContextPath() + "/error");
        }


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
