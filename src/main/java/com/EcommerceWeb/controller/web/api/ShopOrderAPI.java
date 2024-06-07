package com.EcommerceWeb.controller.web.api;

import com.EcommerceWeb.dao.IShopOrderDAO;
import com.EcommerceWeb.model.ShopOrderModel;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.service.IShopOrderService;
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
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api-shop-order"})
public class ShopOrderAPI extends HttpServlet {
    @Inject
    private IShopOrderService shopOrderService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        int idShopOrderInsert = -1;
        SiteUser siteUser = (SiteUser) SessionUtil.getInstance().getValue(req, "SITEUSER");

        if (siteUser == null) {
            ObjectMapper mapper=new ObjectMapper();
            Map<String, Integer> responseMap = new HashMap<>();
            responseMap.put("idShopOrderInsert", -1);
            mapper.writeValue(resp.getOutputStream(), responseMap);
            return;
        }


        try {
            // Lấy dữ liệu từ request body
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = objectMapper.readValue(req.getInputStream(), Map.class);

            // Xử lý dữ liệu
            String description = (String) data.get("description");
            String tempDescription=description;
            if(tempDescription==null || tempDescription.trim().isEmpty()){
                description="Không có ghi chú";
            }

            int paymentMethodID = Integer.parseInt ((String) data.get("paymentMethod"));
            int shippingAddressID = Integer.parseInt ((String) data.get("shippingAddress"));
            int shippingMethodID = Integer.parseInt ((String) data.get("shippingMethod"));

            List<Map<String, String>> items = (List<Map<String, String>>) data.get("items");
            int[] shoppingCartItemIDList = items.stream().mapToInt(item -> Integer.parseInt(item.get("id"))).toArray();


            ShopOrderModel shopOrderModel = new ShopOrderModel();
            shopOrderModel.setUserID(siteUser.getID());
            shopOrderModel.setDescription(description);
            shopOrderModel.setPaymentMethodID(paymentMethodID);
            shopOrderModel.setShippingMethodID(shippingMethodID);
            shopOrderModel.setShippingAddressID(shippingAddressID);


            idShopOrderInsert = shopOrderService.insertBill(shopOrderModel,shoppingCartItemIDList);
        }
        catch (Exception e){
            idShopOrderInsert =  -1;
        }


        ObjectMapper mapper=new ObjectMapper();
        Map<String, Integer> responseMap = new HashMap<>();
        responseMap.put("idShopOrderInsert", idShopOrderInsert);
        mapper.writeValue(resp.getOutputStream(), responseMap);

    }
}
