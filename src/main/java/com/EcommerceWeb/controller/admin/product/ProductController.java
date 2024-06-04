package com.EcommerceWeb.controller.admin.product;

import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductCategory;
import com.EcommerceWeb.service.impl.ProductCategoryService;
import com.EcommerceWeb.service.impl.ProductService;

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

@WebServlet(urlPatterns = {"/admin-product"})
public class ProductController extends HttpServlet {
    @Inject
    private ProductService productService;
    @Inject
    private ProductCategoryService productCategoryService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductCategory> listCategory= productCategoryService.getAll();
        List<Product> listProduct=productService.getAll();
        List<ProductShow> listProductShow=new ArrayList<ProductShow>();
        List<CategoryShow> listCategoryShow= new ArrayList<>();
        for(Product product:listProduct) {
            ProductShow productShow = new ProductShow();
            productShow.setName(product.getDisplayName());
            productShow.setQuantity(productService.getTotalQuantityInStock(product.getID()));
            productShow.setCategory( productService.getCategory(product.getCategoryID()).getCategoryName());
            listProductShow.add(productShow);
        }
        for(ProductCategory productCategory:listCategory) {
            CategoryShow categoryShow = new CategoryShow();
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

        request.setAttribute("listProduct",listProductShow);
        request.setAttribute("listCategory",listCategoryShow);
        RequestDispatcher rd = request.getRequestDispatcher("views/admin/product/productList.jsp");
        rd.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        private String name;
        private String parent;

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