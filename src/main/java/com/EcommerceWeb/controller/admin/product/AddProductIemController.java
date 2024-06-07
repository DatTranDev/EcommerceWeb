package com.EcommerceWeb.controller.admin.product;

import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductCategory;
import com.EcommerceWeb.model.ProductItem;
import com.EcommerceWeb.service.impl.ProductCategoryService;
import com.EcommerceWeb.service.impl.ProductItemService;
import com.EcommerceWeb.service.impl.ProductService;

import javax.inject.Inject;
import javax.servlet.HttpConstraintElement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/admin-addProductItem/*"})
public class AddProductIemController extends HttpServlet {
    @Inject
    private ProductService productService;
    @Inject
    private ProductCategoryService productCategoryService;
    @Inject
    private ProductItemService productItemService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pathInfo = request.getPathInfo();
            List<String> listImage = new ArrayList<>();
            String productId = null;
            if (pathInfo != null) {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length > 1) {
                    productId = pathParts[1];
                    int id = Integer.parseInt(productId);
                    Product product = productService.findOne(id);
                    if (product != null) {
                        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product/addProductItem.jsp");
                        rd.forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/error");
                    }
                } else {
                    response.sendRedirect(request.getContextPath() + "/error");
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/error");
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
