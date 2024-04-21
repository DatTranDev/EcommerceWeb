package com.EcommerceWeb.controller.web;

import com.EcommerceWeb.service.IProductCategoryService;
import com.EcommerceWeb.service.IProductService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/product-collections"})
public class ProductController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private IProductService productService;
    @Inject
    private IProductCategoryService productCategoryService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        request.setAttribute("ProductCategory", productCategoryService.getAll());
        request.setAttribute("Product", productService.getAll());
        RequestDispatcher rd = request.getRequestDispatcher("/views/web/product-collections.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    }
}
