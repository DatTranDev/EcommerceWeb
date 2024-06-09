package com.EcommerceWeb.controller.admin.category;

import com.EcommerceWeb.service.impl.ProductCategoryService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-deleteCategory/*"})
public class DeleteCategory extends HttpServlet {
    @Inject
    private ProductCategoryService productCategoryService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        if (pathParts.length > 1) {
            int id = Integer.parseInt(pathParts[1]);
            productCategoryService.delete(id);
            response.sendRedirect(request.getContextPath() + "/admin-product");
        }
        else
        {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String[] pathParts = pathInfo.split("/");
        if (pathParts.length > 1) {
            int id = Integer.parseInt(pathParts[1]);
            productCategoryService.delete(id);
            response.sendRedirect(request.getContextPath() + "/admin-product");
        }
        else
        {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
