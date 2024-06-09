package com.EcommerceWeb.controller.admin.product;

import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductCategory;
import com.EcommerceWeb.service.impl.ProductCategoryService;
import com.EcommerceWeb.service.impl.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
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

@WebServlet(urlPatterns = {"/admin-product/add"})
public class AddProductController extends HttpServlet {
    @Inject
    private ProductService productService;
    @Inject
    private ProductCategoryService productCategoryService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductCategory> listCategory= productCategoryService.getAll();
        List<ProductController.CategoryShow> listCategoryShow= new ArrayList<>();
        for(ProductCategory productCategory:listCategory) {
            ProductController.CategoryShow categoryShow = new ProductController.CategoryShow();
            categoryShow.setId(productCategory.getID());
            categoryShow.setName(productCategory.getCategoryName());
            if(productCategory.getParentCategoryID()!=0)
            {
                categoryShow.setParent(" ("+ productCategoryService.findOne(productCategory.getParentCategoryID()).getCategoryName()+")");
            }
            else
            {
                categoryShow.setParent("");
            }
            listCategoryShow.add(categoryShow);
        }

        request.setAttribute("listCategory",listCategoryShow);
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product/addProduct.jsp");
        rd.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name= request.getParameter("name");
        String description= request.getParameter("description");
        int categoryID= Integer.parseInt(request.getParameter("category"));
        String jsonImage= request.getParameter("listImage");
        List<String> listImage= new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            listImage= objectMapper.readValue(jsonImage, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/error");
        }
        String image = String.join(", ", listImage);
        Product newProduct = new Product();
        newProduct.setCategoryID(categoryID);
        newProduct.setDisplayName(name);
        newProduct.setDescription(description);
        newProduct.setProductImage(image);
        Product test=productService.add(newProduct);
        if(test!=null)
        {
            response.sendRedirect(request.getContextPath() + "/admin-product");
        }
        else
        {
            response.sendRedirect(request.getContextPath() + "/error");
        }

    }
    public static class ProductShow {
        private String name;
        private int quantity;
        private String category;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }
    public static class CategoryShow {
        private int id;
        private String name;
        private String parent;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getParent() {
            return parent;
        }

        public void setParent(String parent) {
            this.parent = parent;
        }
    }
}
