package com.EcommerceWeb.controller.web.api;

import com.EcommerceWeb.model.ShoppingCartItemModel;
import com.EcommerceWeb.service.IShoppingCartItemService;
import com.EcommerceWeb.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api-cart"})
public class ShoppingCartAPI extends HttpServlet {

    @Inject
    private IShoppingCartItemService shoppingCartItemService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // super.doPut(req, resp);
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
        }
        ObjectMapper mapper=new ObjectMapper();
        Map<String, Boolean> responseMap = new HashMap<>();
        responseMap.put("success", success);
        mapper.writeValue(resp.getOutputStream(), responseMap);
    }

}
