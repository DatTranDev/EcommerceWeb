package com.EcommerceWeb.controller.web;

import com.EcommerceWeb.model.OrderLineModel;
import com.EcommerceWeb.model.ShopOrderModel;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.service.IOrderLineService;
import com.EcommerceWeb.service.IProductItemService;
import com.EcommerceWeb.service.IProductService;
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

@WebServlet(urlPatterns = {"/detail-shop-order"})
public class ShopOrderDetailController extends HttpServlet {
    @Inject
    IProductService iProductService;
    @Inject
    IShopOrderService iShopOrderService;
    @Inject
    IProductItemService iProductItemService;
    @Inject
    IOrderLineService iOrderLineService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("orderId");
        ShopOrderModel shopOrderModel = iShopOrderService.findOne(Integer.parseInt(id));
        List<OrderLineModel> orderLineModelList = iOrderLineService.findByOrderID(Integer.parseInt(id));
        for(OrderLineModel orderLineModel : orderLineModelList){
            orderLineModel.setProductItem(iProductItemService.findOne(orderLineModel.getProductItemID()));
            orderLineModel.setProduct(iProductService.findOne(orderLineModel.getProductItem().getProductID()));
        }
        request.setAttribute("shoppingCartItemModelList", orderLineModelList);
        request.setAttribute("shopOrderModel", shopOrderModel);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/shopOrderDetail.jsp");
            rd.forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
