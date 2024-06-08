package com.EcommerceWeb.controller.web.api;

import com.EcommerceWeb.dao.IProductItemDAO;
import com.EcommerceWeb.model.ProductItem;
import com.EcommerceWeb.model.ShoppingCartItemModel;
import com.EcommerceWeb.model.ShoppingCartModel;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.service.IProductItemService;
import com.EcommerceWeb.service.IShoppingCartItemService;
import com.EcommerceWeb.utils.HttpUtil;
import com.EcommerceWeb.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api-cart"})
public class ShoppingCartAPI extends HttpServlet {

    @Inject
    private IShoppingCartItemService shoppingCartItemService;
    @Inject
    private IProductItemService productItemService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPost(req, resp);
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

                    ShoppingCartItemModel shoppingCartItemModel1 = new ShoppingCartItemModel();
                   if(shoppingCartItemService.insert(shoppingCartItemModel1)==null){
                       success=false;
                   }
                   else{

                   }
                }
                else{

                }
            }


        }
        catch (Exception e){}



    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        boolean success=false;

        try{

            ShoppingCartItemModel shoppingCartItemModel = HttpUtil.of(req.getReader()).toModel(ShoppingCartItemModel.class);
            if(shoppingCartItemModel!=null) {
                for (String item : shoppingCartItemModel.getListUpdate()) {
                    success=true;
                    String[] s = item.trim().split("-");
                    int shoppingCartItemID = Integer.parseInt(s[0]);
                    int quantityUpdate = Integer.parseInt(s[1]);

                    ShoppingCartItemModel itemUpdate=shoppingCartItemService.findOne(shoppingCartItemID);
                    if(itemUpdate==null){
                        success=false;
                        break;
                    }
                    else {
                        itemUpdate.setQuantity(quantityUpdate);
                        if (shoppingCartItemService.update(itemUpdate) == null) {
                            success = false;
                            break;
                        }
                    }
                }
            }

        }
        catch (Exception e){success=false;}

        ObjectMapper mapper=new ObjectMapper();
        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("success", success);
        mapper.writeValue(resp.getOutputStream(), responseMap);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        boolean success=false;
        try {
            ShoppingCartItemModel shoppingCartItemModel = HttpUtil.of(req.getReader()).toModel(ShoppingCartItemModel.class);
            success = shoppingCartItemService.updateListItemIsDeleteTrue(shoppingCartItemModel.getIds());
        } catch (Exception e) {
            success=false;
        }
        ObjectMapper mapper=new ObjectMapper();
        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("success", success);
        mapper.writeValue(resp.getOutputStream(), responseMap);
    }

}
