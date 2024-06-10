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



          List<ShopOrderModel> shopOrderModelList = shopOrderService.findAllByUserID(siteUser.getID());
          List<ShopOrderModel> prepareShopOrderList = shopOrderService.findAllShopOderByUserIdAndOrderStatusId(siteUser.getID(),1);
          List<ShopOrderModel> deliveryShopOrderList = shopOrderService.findAllShopOderByUserIdAndOrderStatusId(siteUser.getID(),2);
          List<ShopOrderModel> successShopOrderList = shopOrderService.findAllShopOderByUserIdAndOrderStatusId(siteUser.getID(),3);
          List<ShopOrderModel> failShopOrderList = shopOrderService.findAllShopOderByUserIdAndOrderStatusId(siteUser.getID(),4);
          List<ShopOrderModel> cancelShopOrderList = shopOrderService.findAllShopOderByUserIdAndOrderStatusId(siteUser.getID(),5);

        if (shopOrderModelList == null) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
        else{

            String orderStatusName="";
            String describeOrder="";
            for(ShopOrderModel shopOrderModel : shopOrderModelList) {
                if(shopOrderModel.getOrderStatusID()==1){
                    describeOrder="Đang chuẩn bị";
                    orderStatusName="Đang chuẩn bị";

                }else if(shopOrderModel.getOrderStatusID()==2){
                    describeOrder="Đang vận chuyển";
                    orderStatusName="Đang vận chuyển";
                }else if(shopOrderModel.getOrderStatusID()==3){
                    describeOrder="Mua lại";
                    orderStatusName="Giao thành công";
                }else if(shopOrderModel.getOrderStatusID()==5){
                    describeOrder="Mua lại";
                    orderStatusName="Đã hủy";
                }
                shopOrderModel.setStatusName(orderStatusName);
                shopOrderModel.setDescribeOrder(describeOrder);
                System.out.println(shopOrderModel.getStatusName()+" "+shopOrderModel.getOrderStatusID());
            }


            request.setAttribute("shopOrderModelList", shopOrderModelList);
            request.setAttribute("prepareShopOrderList", prepareShopOrderList);
            request.setAttribute("deliveryShopOrderList", deliveryShopOrderList);
            request.setAttribute("successShopOrderList", successShopOrderList);
            request.setAttribute("failShopOrderList", failShopOrderList);
            request.setAttribute("cancelShopOrderList", cancelShopOrderList);

            RequestDispatcher rd = request.getRequestDispatcher("/views/web/PurchaseHistory.jsp");
            rd.forward(request, response);




        }


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
