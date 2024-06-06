package com.EcommerceWeb.controller.web;

import com.EcommerceWeb.dao.IShippingMethodDAO;
import com.EcommerceWeb.model.ShippingMethod;
import com.EcommerceWeb.model.ShoppingCartItemModel;
import com.EcommerceWeb.model.SiteUser;
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
        SiteUser model = FormUtil.toModel(SiteUser.class, request);
        model = siteUserService.findByUserNameAndPassword("user@gmail.com", "user");
        if(model!=null) {
            SessionUtil.getInstance().putValue(request, "SITEUSER", model);
        }




        //lay user o session hien tai
        SiteUser siteUser=(SiteUser)(SessionUtil.getInstance().getValue(request, "SITEUSER"));
        if(siteUser==null){
            response.sendRedirect(request.getContextPath() + "/error");
            return;
        }

        //lay toan bo item co trong gio hang
        List<ShoppingCartItemModel> shoppingCartItemModelList = shoppingCartItemService.findAllByUserID(siteUser.getID());
        if(shoppingCartItemModelList==null){
            response.sendRedirect(request.getContextPath() + "/error");
            return;
        }
        //bind sang jsp
        request.setAttribute("shoppingCartItemModelList", shoppingCartItemModelList);


        RequestDispatcher rd = request.getRequestDispatcher("/views/web/cart.jsp");
        rd.forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    }
}
