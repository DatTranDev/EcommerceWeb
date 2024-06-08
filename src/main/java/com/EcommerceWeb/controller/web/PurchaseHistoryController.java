package com.EcommerceWeb.controller.web;

import com.EcommerceWeb.model.OrderLineModel;
import com.EcommerceWeb.model.ProductItem;
import com.EcommerceWeb.model.ShopOrderModel;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.service.IShopOrderService;
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


@WebServlet(urlPatterns = {"/purchase-history"})
public class PurchaseHistoryController extends HttpServlet {

    @Inject
    private IShopOrderService shopOrderService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        SiteUser siteUser = (SiteUser) SessionUtil.getInstance().getValue(request, "SITEUSER");

        if(siteUser == null) {
            response.sendRedirect(request.getContextPath() + "/error");
            return;
        }


       // String result = request.getParameter("idShopOrder").trim();

//        int idShopOrder;
//        if (result != null && !result.isEmpty()) {
//            idShopOrder=Integer.parseInt(result);
//
//
//
//
//
//            RequestDispatcher rd = request.getRequestDispatcher("/views/web/PurchaseHistory.jsp");
//            rd.forward(request, response);
//        }
//        else{
//            response.sendRedirect(request.getContextPath() + "/error");
//        }

          List<ShopOrderModel> shopOrderModelList = shopOrderService.findAllByUserID(siteUser.getID());

        if (shopOrderModelList == null) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
        else{


            for(ShopOrderModel shopOrderModel : shopOrderModelList) {
                shopOrderModel.getOrderTotal();

                for (OrderLineModel orderLineModel : shopOrderModel.getListOrderLine()){

                    ProductItem productItem = orderLineModel.getProductItem();
                    orderLineModel.getProductItem().getProduct().getDisplayName();
                    orderLineModel.getProductItem().getPrice();
                    orderLineModel.getQuantity();
                    orderLineModel.getProductItem().getListProductConfig();



                   System.out.println(productItem.getID()+"===="+productItem.getProduct().getDisplayName());
                }
            }


            request.setAttribute("shopOrderModelList", shopOrderModelList);

            RequestDispatcher rd = request.getRequestDispatcher("/views/web/PurchaseHistory.jsp");
            rd.forward(request, response);




        }


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
