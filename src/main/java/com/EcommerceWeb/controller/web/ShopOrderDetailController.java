package com.EcommerceWeb.controller.web;

import com.EcommerceWeb.model.OrderLineModel;
import com.EcommerceWeb.model.ShopOrderModel;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.service.impl.OrderLineService;
import com.EcommerceWeb.service.impl.ShopOrderService;
import com.EcommerceWeb.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/detail-shop-order"})
public class ShopOrderDetailController extends HttpServlet {
    @Inject
    private ShopOrderService shopOrderService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //lay id cua don hang tu request
        String id = request.getParameter("orderId");
        //tim danh sach san pham co trong don hang
        ShopOrderModel shopOrderModel = shopOrderService.findOne(Integer.parseInt(id));
        //tinh chi phi van chuyen
        double chiPhiVanChuyen = 0;
        if(shopOrderModel.getShippingMethod().getID()==1){
            chiPhiVanChuyen = 50000;
        }else if(shopOrderModel.getShippingMethod().getID()==2){
            chiPhiVanChuyen = 30000;
        }else if(shopOrderModel.getShippingMethod().getID()==3){
            chiPhiVanChuyen = 80000;
        }
        //truyen don hang qua jsp
        request.setAttribute("shopOrderModel", shopOrderModel);
        request.setAttribute("chiPhiVanChuyen", chiPhiVanChuyen);

        RequestDispatcher rd = request.getRequestDispatcher("/views/web/shopOrderDetail.jsp");
        rd.forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
