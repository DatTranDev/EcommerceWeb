package com.EcommerceWeb.controller.admin.api.shoporder;


import com.EcommerceWeb.dao.IShopOrderDAO;
import com.EcommerceWeb.model.OrderLineModel;
import com.EcommerceWeb.model.ShopOrderModel;
import com.EcommerceWeb.service.IShopOrderService;
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

@WebServlet(urlPatterns = {"/api-admin-shop-order-dang-chuan-bi"})

public class ShopOrderDangChuanBiAPI extends HttpServlet {


    @Inject
    IShopOrderService shopOrderService;
    @Inject
    IShopOrderDAO shopOrderDAO;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //  super.doPost(req, resp);

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        boolean success = true;

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = objectMapper.readValue(req.getInputStream(), Map.class);

            // Xử lý dữ liệu
            String description = (String) data.get("notes");
            String tempDescription=description;
            if(tempDescription==null || tempDescription.trim().isEmpty()){
                description="Không có ghi chú";
            }
            int orderId;
            if(data.get("orderId") instanceof  String) {
                orderId = Integer.parseInt((String) data.get("orderId"));
            }
            else{
                orderId =(int)data.get("orderId");
            }
            ShopOrderModel shopOrderModel = shopOrderService.findOne(orderId);
            if(shopOrderModel==null){
                success = false;
            }
            else{
                if(!description.equals("deleteOnShopOrder.jsp")) {
                    shopOrderModel.setDescription(description);
                }
                shopOrderModel.setOrderStatusID(5);
                shopOrderDAO.update(shopOrderModel);

                ShopOrderModel check = shopOrderService.findOne(orderId);
                if(check==null){
                    success=false;
                }
                else{
                    if(check.getOrderStatusID()!=5){
                        success=false;
                    }
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


    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        boolean success = true;

        try{
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> data = objectMapper.readValue(req.getInputStream(), Map.class);
            int orderId;
            if(data.get("orderId") instanceof  String) {
                orderId = Integer.parseInt((String) data.get("orderId"));
            }
            else{
                orderId =(int)data.get("orderId");
            }
            ShopOrderModel shopOrderModel = shopOrderService.findOne(orderId);
            if(shopOrderModel==null){
                success = false;
            }
            else{

                for(OrderLineModel orderLineModel: shopOrderModel.getListOrderLine()){
                    if(orderLineModel.getQuantity() > orderLineModel.getProductItem().getQuantityInStock()){
                        success=false;
                        break;
                    }
                }
                if(success){
                    shopOrderModel.setOrderStatusID(2);
                    shopOrderDAO.update(shopOrderModel);

                    ShopOrderModel check = shopOrderService.findOne(orderId);
                    if(check==null){
                        success=false;
                    }
                    else{
                        if(check.getOrderStatusID()!=2){
                            success=false;
                        }
                    }
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
