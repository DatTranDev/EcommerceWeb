package com.EcommerceWeb.controller.admin;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-shop-order"})

public class ShopOrderController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String status=req.getParameter("status");

        if(status==null){
            RequestDispatcher rd = req.getRequestDispatcher("/views/admin/error.jsp");
            rd.forward(req, resp);
        }
        else{

            status=status.trim();

            if(status.equals("waiting")){
                System.out.println(status);

            }else if(status.equals("shipping")){
                System.out.println(status);


            }else if(status.equals("delivered")){
                System.out.println(status);


            }else if(status.equals("failed")){
                System.out.println(status);

            }
            else if(status.equals("canceled")){
                System.out.println(status);

            }else{
                RequestDispatcher rd = req.getRequestDispatcher("/views/admin/error.jsp");
                rd.forward(req, resp);
                return;
            }
        }
    }
}
