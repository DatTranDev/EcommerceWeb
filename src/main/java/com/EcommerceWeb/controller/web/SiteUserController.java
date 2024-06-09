package com.EcommerceWeb.controller.web;


import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.model.UserAddress;
import com.EcommerceWeb.service.IUserAddressService;
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

@WebServlet(urlPatterns = {"/site-user"})

public class SiteUserController extends HttpServlet {

    @Inject
    private IUserAddressService userAddressService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean susscess = true;

        SiteUser siteUser = (SiteUser) SessionUtil.getInstance().getValue(req, "SITEUSER");
        if(siteUser==null){
            susscess=false;
        }
        else {

            System.out.println(siteUser.getUserName()+"--"+siteUser.getBirthDay());

            List<UserAddress> userAddressList = userAddressService.findByUserID(siteUser.getID());
            if(userAddressList==null){
                susscess=false;
            }
            else {
                req.setAttribute("siteUser", siteUser);
                req.setAttribute("userAddressList",userAddressList);
                RequestDispatcher rd = req.getRequestDispatcher("/views/web/information.jsp");
                rd.forward(req, resp);
                return;
            }
        }

        if(!susscess){
            RequestDispatcher rd = req.getRequestDispatcher("/views/error.jsp");
            rd.forward(req, resp);
        }

    }
}
