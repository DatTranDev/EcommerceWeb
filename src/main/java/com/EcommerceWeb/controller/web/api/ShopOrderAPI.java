package com.EcommerceWeb.controller.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/api-shop-order"})
public class ShopOrderAPI extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        // Lấy dữ liệu từ request body
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> data = objectMapper.readValue(req.getInputStream(), Map.class);

        // Xử lý dữ liệu
        String description = (String) data.get("description");
        String paymentMethod = (String) data.get("paymentMethod");
        String shippingAddress = (String) data.get("shippingAddress");
        String shippingMethod = (String) data.get("shippingMethod");
        List<Map<String, String>> items = (List<Map<String, String>>) data.get("items");

        // Ví dụ xử lý: In dữ liệu ra console (hoặc lưu vào database, v.v.)
        System.out.println("Description: " + description);
        System.out.println("Payment Method: " + paymentMethod);
        System.out.println("Shipping Address: " + shippingAddress);
        System.out.println("Shipping Method: " + shippingMethod);
        System.out.println("Items: " + items);

        // Trả về kết quả
        resp.getWriter().write("{\"success\": true}");
    }
}
