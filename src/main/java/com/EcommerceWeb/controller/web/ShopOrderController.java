package com.EcommerceWeb.controller.web;

import com.EcommerceWeb.model.*;
import com.EcommerceWeb.service.*;
import com.EcommerceWeb.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = {"/shop-order"})
public class ShopOrderController extends HttpServlet {

    @Inject
    private IShoppingCartItemService shoppingCartItemService;
    @Inject
    private IPaymentMethodService paymentMethodService;
    @Inject
    private IShippingMethodService shippingMethodService;
    @Inject
    private IUserAddressService userAddressService;


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedIds = req.getParameter("selectedIds");
        boolean success = true;
        SiteUser siteUser = (SiteUser) SessionUtil.getInstance().getValue(req, "SITEUSER");

        if (siteUser == null || selectedIds == null || selectedIds.isEmpty()) {
            success = false;
        }
        req.setAttribute("siteUser", siteUser);



        if (success) {

            try {
                int[] ids = Arrays.stream(selectedIds.split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                List<ShoppingCartItemModel> shoppingCartItemModelList = shoppingCartItemService.findByListShoppingCartItemID(ids);
                //lay toan bo item co trong phuong thuc thanh toan
                List<PaymentMethod> paymentMethodList = paymentMethodService.findAllNotWhereIsDelete();
                //lay toan bo dia chi cua userCur
                List<UserAddress> userAddressList = userAddressService.findByUserID(siteUser.getID());
                //lay toan bo phuong thuc van chuyen
                List<ShippingMethod> shippingMethodList = shippingMethodService.findAllNotWhereIsDelete();

                if(shoppingCartItemModelList==null || paymentMethodList==null || userAddressList==null || shippingMethodList==null){
                    resp.sendRedirect(req.getContextPath() + "/error");
                    return;
                }
                //bind sang jsp
                req.setAttribute("shoppingCartItemModelList", shoppingCartItemModelList);
                req.setAttribute("paymentMethodList", paymentMethodList);
                req.setAttribute("userAddressList", userAddressList);
                req.setAttribute("shippingMethodList", shippingMethodList);

            }
            catch (Exception e){
                resp.sendRedirect(req.getContextPath() + "/error.jsp");
                return;
            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("/views/web/checkout.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/error.jsp");
        }
    }
}
