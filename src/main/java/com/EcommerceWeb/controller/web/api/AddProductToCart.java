package com.EcommerceWeb.controller.web.api;

import com.EcommerceWeb.model.ShoppingCartItemModel;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.service.IShoppingCartItemService;
import com.EcommerceWeb.service.impl.SiteUserService;
import com.EcommerceWeb.utils.FormUtil;
import com.EcommerceWeb.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/api-add_product_to_cart"})
public class AddProductToCart extends HttpServlet {
    @Inject
    private IShoppingCartItemService shoppingCartItemService;
    @Inject
    private SiteUserService siteUserService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        //test
//        SiteUser model = FormUtil.toModel(SiteUser.class, req);
//        model = siteUserService.findByUserNameAndPassword("user@gmail.com", "user");
//        if(model!=null) {
//            SessionUtil.getInstance().putValue(req, "SITEUSER", model);
//        }


        SiteUser model = (SiteUser) SessionUtil.getInstance().getValue(req, "SITEUSER");

        boolean success=true;
        boolean isLogin=true;
        if(model==null){
            success=false;
            isLogin = false;
        }



        boolean typeAddTocart=true;//true la insert false la update

        int soluongdangco=-1;
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

                    if(shoppingCartItemService.insertFix(model.getID(),productItemID,quantity)==null){
                        success=false;
                    }
                }
                else{

                    typeAddTocart = false;
                    soluongdangco= shoppingCartItemModel.getQuantity();
//                    shoppingCartItemModel.setQuantity(shoppingCartItemModel.getQuantity()+quantity);
//                    if(shoppingCartItemService.updateFix(shoppingCartItemModel)==null){
//                        success=false;
//                    }
                }
            }


        }
        catch (Exception e){
            success=false;
        }

        ObjectMapper mapper=new ObjectMapper();
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", success);
        responseMap.put("isLogin", isLogin);
        responseMap.put("typeAddTocart", typeAddTocart);
        responseMap.put("soluongdangco", soluongdangco);
        mapper.writeValue(resp.getOutputStream(), responseMap);
    }


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");


        SiteUser siteUser = (SiteUser) SessionUtil.getInstance().getValue(req, "SITEUSER");


        boolean success=true;
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
                if(shoppingCartItemModel.getID()!=-1){
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
        Map<String, Object> responseMap = new HashMap<>();
        responseMap.put("success", success);
        mapper.writeValue(resp.getOutputStream(), responseMap);
    }
}
