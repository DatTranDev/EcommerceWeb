package com.EcommerceWeb.controller.web.api;

import com.EcommerceWeb.model.ShoppingCartItemModel;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.service.IShoppingCartItemService;
import com.EcommerceWeb.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/api-add_product_to_cart"})
public class AddProductToCart extends HttpServlet {
    @Inject
    private IShoppingCartItemService shoppingCartItemService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        boolean success=true;
        SiteUser siteUser = (SiteUser) SessionUtil.getInstance().getValue(req, "SITEUSER");
        if(siteUser==null){
            success=false;
        }



        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = objectMapper.readValue(req.getInputStream(), Map.class);

            int productItemID= Integer.parseInt(((String)data.get("productItemId")));
            int quantity=Integer.parseInt(((String)data.get("quantity")));

            ShoppingCartItemModel shoppingCartItemModel = shoppingCartItemService.findOneByProductItemIdFix(productItemID);
            if(shoppingCartItemModel==null){
                success=false;
            }
            else{
                //chua co trong gio hang
                if(shoppingCartItemModel.getID()==-1){

                    if(shoppingCartItemService.insertFix(siteUser.getID(),productItemID,quantity)==null){
                        success=false;
                    }
                }
                else{
                    shoppingCartItemModel.setQuantity(shoppingCartItemModel.getQuantity()+quantity);
                    if(shoppingCartItemService.updateFix(shoppingCartItemModel)==null){
                        success=false;
                    }
                }
            }


        }
        catch (Exception e){
            success=false;
        }

        ObjectMapper mapper=new ObjectMapper();
        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("success", success);
        mapper.writeValue(resp.getOutputStream(), responseMap);

    }
}
