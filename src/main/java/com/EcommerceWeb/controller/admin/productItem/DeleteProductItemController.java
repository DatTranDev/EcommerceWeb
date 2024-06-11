package com.EcommerceWeb.controller.admin.productItem;

import com.EcommerceWeb.service.impl.ProductCategoryService;
import com.EcommerceWeb.service.impl.ProductItemService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-deleteProductItem/*"})
public class DeleteProductItemController extends HttpServlet {
    @Inject
    private ProductCategoryService productCategoryService;
    @Inject
    private ProductItemService productItemService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();
        String url = pathInfo;
        int id=0;
        String[] parts = url.split("/");
        id = Integer.parseInt(parts[1]);
        if (parts.length > 2)
        {
            String[] numbersStr = parts[2].split(",");
            List<Integer> integerList = new ArrayList<>();

            for (String numberStr : numbersStr) {
                integerList.add(Integer.parseInt(numberStr.trim()));
            }
            if(integerList.size()>0)
            {
                for(Integer integer:integerList)
                {
                    productItemService.delete(integer);
                }
                response.sendRedirect(request.getContextPath() + "/admin-editProduct/"+id);
            }
        }
        else {
            String successMessage = "Xóa thất bại";
            String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
            HttpSession session = request.getSession();
            session.setAttribute("alert", encodedMessage);
            response.sendRedirect(request.getContextPath() + "/admin-editProduct/"+id);
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/admin-product");
//        String pathInfo = request.getPathInfo();
//        String[] pathParts = pathInfo.split("/");
//        if (pathParts.length > 1) {
//            int id = Integer.parseInt(pathParts[1]);
//            productCategoryService.delete(id);
//            response.sendRedirect(request.getContextPath() + "/admin-product");
//        }
//        else
//        {
//            response.sendRedirect(request.getContextPath() + "/error");
//        }
    }
}