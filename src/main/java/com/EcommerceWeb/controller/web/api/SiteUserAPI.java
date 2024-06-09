package com.EcommerceWeb.controller.web.api;

import com.EcommerceWeb.model.Address;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.model.UserAddress;
import com.EcommerceWeb.service.IAddressService;
import com.EcommerceWeb.service.IUserAddressService;
import com.EcommerceWeb.service.impl.UserAddressService;
import com.EcommerceWeb.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@WebServlet(urlPatterns = {"/api-site-user"})
public class SiteUserAPI extends HttpServlet {

    @Inject
    private IUserAddressService userAddressService;
    @Inject
    private IAddressService addressService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        boolean success = true;


        try {
            SiteUser siteUser = (SiteUser) SessionUtil.getInstance().getValue(req, "SITEUSER");
            if (siteUser == null) {
                success = false;
            } else {


                ObjectMapper objectMapper = new ObjectMapper();
                Map<String, Object> data = objectMapper.readValue(req.getInputStream(), Map.class);
                String newAddress = (String) data.get("newAddress");

                Address address = new Address();
                address.setValue(newAddress);
                UserAddress userAddress = new UserAddress();
                userAddress.setUserID(siteUser.getID());
                userAddress.setDefault(true);

                if(userAddressService.insert(userAddress,address)==null)
                {
                    success=false;
                }


            }
        }
        catch (Exception e){
            success = false;
        }

        ObjectMapper mapper=new ObjectMapper();
        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("success", success);
        mapper.writeValue(resp.getOutputStream(), responseMap);


    }
}
