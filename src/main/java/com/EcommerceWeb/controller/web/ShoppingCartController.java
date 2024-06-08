package com.EcommerceWeb.controller.web;

import com.EcommerceWeb.model.*;
import com.EcommerceWeb.service.*;
import com.EcommerceWeb.utils.FormUtil;
import com.EcommerceWeb.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;


@WebServlet (urlPatterns = {"/cart"})
public class ShoppingCartController extends HttpServlet {


    @Inject
    private IShoppingCartService shoppingCartService;
    @Inject
    private IShoppingCartItemService shoppingCartItemService;
    @Inject
    private ISiteUserService siteUserService;
    @Inject
    private IPaymentMethodService paymentMethodService;
    @Inject
    private IShippingMethodService shippingMethodService;
    @Inject
    private IUserAddressService userAddressService;


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {


        //test
        //SiteUser model = FormUtil.toModel(SiteUser.class, request);
        //model = siteUserService.findByUserNameAndPassword("user@gmail.com", "user");
        SiteUser model = (SiteUser) SessionUtil.getInstance().getValue(request, "SITEUSER");
        if(model==null) {
            String loginUrl = request.getContextPath() + "/dang-nhap?action=login&message=" + URLEncoder.encode("Vui lòng đăng nhập trước", "UTF-8") + "&alert=danger";
            response.sendRedirect(loginUrl);
            return;
        }




        //lay user o session hien tai
        SiteUser siteUser=(SiteUser)(SessionUtil.getInstance().getValue(request, "SITEUSER"));
        if(siteUser==null){
            response.sendRedirect(request.getContextPath() + "/error");
            return;
        }
        request.setAttribute("siteUser", siteUser);



        //lay toan bo item co trong gio hang
        List<ShoppingCartItemModel> shoppingCartItemModelList = shoppingCartItemService.findAllByUserID(siteUser.getID());
        //lay toan bo item co trong phuong thuc thanh toan
        List<PaymentMethod> paymentMethodList = paymentMethodService.findAllNotWhereIsDelete();
        //lay toan bo dia chi cua userCur
        List<UserAddress> userAddressList = userAddressService.findByUserID(siteUser.getID());
        //lay toan bo phuong thuc van chuyen
        List<ShippingMethod> shippingMethodList = shippingMethodService.findAllNotWhereIsDelete();


        if(shoppingCartItemModelList==null || paymentMethodList==null || userAddressList==null || shippingMethodList==null){
            response.sendRedirect(request.getContextPath() + "/error");
            return;
        }
        //bind sang jsp
        request.setAttribute("shoppingCartItemModelList", shoppingCartItemModelList);
        request.setAttribute("paymentMethodList", paymentMethodList);
        request.setAttribute("userAddressList", userAddressList);
        request.setAttribute("shippingMethodList", shippingMethodList);
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/cart.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    }
}
