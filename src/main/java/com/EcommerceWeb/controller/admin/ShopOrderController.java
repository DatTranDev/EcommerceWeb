package com.EcommerceWeb.controller.admin;


import com.EcommerceWeb.model.ShopOrderModel;
import com.EcommerceWeb.service.IShopOrderService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-shop-order"})

public class ShopOrderController extends HttpServlet {
    @Inject
    private IShopOrderService shopOrderService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String status=req.getParameter("status");

        if(status==null){
            RequestDispatcher rd = req.getRequestDispatcher("/views/admin/error.jsp");
            rd.forward(req, resp);
        }
        else{

            String statusShopOder=null;
            status=status.trim();

            int orderStatusID=0;

            if(status.equals("waiting")){
                statusShopOder = "Chờ xác nhận";
                orderStatusID=1;
            }else if(status.equals("shipping")){
                statusShopOder = "Đang vận chuyển";
                orderStatusID=2;

            }else if(status.equals("delivered")){
                statusShopOder = "Giao hàng thành công";
                orderStatusID=3;

            }else if(status.equals("failed")){
                statusShopOder = "Giao hàng thất bại";
                orderStatusID=4;

            }
            else if(status.equals("canceled")){
                statusShopOder = "Đã hủy";
                orderStatusID=5;

            }else{
                RequestDispatcher rd = req.getRequestDispatcher("/views/admin/error.jsp");
                rd.forward(req, resp);
                return;
            }

            if(statusShopOder!=null){
                req.setAttribute("statusShopOder",statusShopOder);

                List<ShopOrderModel> shopOrderModelList  = shopOrderService.findAllByOrderStatusID(orderStatusID);
                if(shopOrderModelList==null){
                    RequestDispatcher rd = req.getRequestDispatcher("/views/admin/error.jsp");
                    rd.forward(req, resp);
                    return;
                }

                req.setAttribute("shopOrderModelList",shopOrderModelList);


                RequestDispatcher rd = req.getRequestDispatcher("/views/admin/shop_order/shopOrder.jsp");
                rd.forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String id = req.getParameter("id");

        switch (action) {
            case "detail":
                showDetail(req, resp, id);
                break;
            case "accept":
                acceptOrder(req, resp, id);
                break;
            case "delete":
                deleteOrder(req, resp, id);
                break;
            default:
                RequestDispatcher rd = req.getRequestDispatcher("/views/admin/error.jsp");
                rd.forward(req, resp);
        }

    }
    private void showDetail(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {

        boolean sus=true;
        try {
            int intShopOrder = Integer.parseInt(id.trim());

            ShopOrderModel shopOrderModel = shopOrderService.findOne(intShopOrder);
            if(shopOrderModel==null){
                sus=false;
            }
            else {


                request.setAttribute("shopOrderModel",shopOrderModel);

                request.getRequestDispatcher("/views/admin/shop_order/shopOrderDetail.jsp").forward(request, response);
            }
        }
        catch (Exception e){
            sus=false;
        }

        if(!sus){
            request.getRequestDispatcher("/views/admin/error.jsp").forward(request, response);
        }

    }

    private void acceptOrder(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        response.sendRedirect("successPage.jsp");
    }

    private void deleteOrder(HttpServletRequest request, HttpServletResponse response, String id) throws ServletException, IOException {
        response.sendRedirect("successPage.jsp");
    }
}
