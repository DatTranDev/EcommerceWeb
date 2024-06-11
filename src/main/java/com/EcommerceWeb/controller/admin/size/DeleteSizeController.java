package com.EcommerceWeb.controller.admin.size;

import com.EcommerceWeb.service.impl.ProductCategoryService;
import com.EcommerceWeb.service.impl.VariationOptionService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(urlPatterns = {"/admin-deleteSize/*"})
public class DeleteSizeController extends HttpServlet {
    @Inject
    private VariationOptionService variationOptionService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        if (pathParts.length > 1) {
            int id = Integer.parseInt(pathParts[1]);
            if(variationOptionService.delete(id))
            {
                response.sendRedirect(request.getContextPath() + "/admin-size");
            }
            else
            {
                String successMessage = "Xóa thất bại";
                String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
                HttpSession session = request.getSession();
                session.setAttribute("alert", encodedMessage);
                response.sendRedirect(request.getContextPath() + "/admin-size");
                return;
            }
        }
        else
        {
            String successMessage = "Đã xảy ra lỗi";
            String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
            HttpSession session = request.getSession();
            session.setAttribute("alert", encodedMessage);
            response.sendRedirect(request.getContextPath() + "/admin-size");
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

