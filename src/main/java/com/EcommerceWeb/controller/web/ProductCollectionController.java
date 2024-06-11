package com.EcommerceWeb.controller.web;

import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.service.IProductCategoryService;
import com.EcommerceWeb.service.IProductService;
import com.EcommerceWeb.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/product-collections"})
public class ProductCollectionController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private IProductService productService;
    @Inject
    private IProductCategoryService productCategoryService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String id = request.getParameter("categoryID");
        if(id!=null)
        {
            request.setAttribute("ProductCategory", productCategoryService.getAll());
            List<Product> productList = productService.getAll();
            List<Product> prdCate = new ArrayList<>();
            for(Product product : productList){
                product.setMinPrice(productService.getMinPrice(product.getID()));
                if(product.getCategoryID() == Integer.parseInt(id)){
                    prdCate.add(product);
                }
            }
            request.setAttribute("Product", prdCate);
            request.setAttribute("ShoesCategory", SessionUtil.getInstance().getValue(request, "ShoesCategory"));
            request.setAttribute("AccessoriesCategory", SessionUtil.getInstance().getValue(request, "AccessoriesCategory"));
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(prdCate);
            request.setAttribute("json", json);
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/productCollections.jsp");
            rd.forward(request, response);
        }
        else{
            SessionUtil.getInstance().getValue(request, "ProductCategory");
            SessionUtil.getInstance().getValue(request, "Product");
            request.setAttribute("ProductCategory", productCategoryService.getAll());
            List<Product> productList = productService.getAll();
            for(Product product : productList){
                product.setMinPrice(productService.getMinPrice(product.getID()));
            }
            request.setAttribute("Product", productList);
            request.setAttribute("ShoesCategory", SessionUtil.getInstance().getValue(request, "ShoesCategory"));
            request.setAttribute("AccessoriesCategory", SessionUtil.getInstance().getValue(request, "AccessoriesCategory"));
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(productList);
            request.setAttribute("json", json);
            RequestDispatcher rd = request.getRequestDispatcher("/views/web/productCollections.jsp");
            rd.forward(request, response);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    }
}
