package com.EcommerceWeb.controller.admin.category;

import com.EcommerceWeb.controller.admin.product.EditProductController;
import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductCategory;
import com.EcommerceWeb.model.ProductItem;
import com.EcommerceWeb.service.impl.ProductCategoryService;
import com.EcommerceWeb.service.impl.ProductItemService;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/admin-editCategory/*"})
public class EditCategoryController extends HttpServlet {
    @Inject
    private ProductService productService;
    @Inject
    private ProductCategoryService productCategoryService;
    @Inject
    private ProductItemService productItemService;
    @Inject
    private ProductCategory productCategory;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String categoryId = null;

        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1) {
                categoryId = pathParts[1];
                int id = Integer.parseInt(categoryId);
                productCategory = productCategoryService.findOne(id);
                request.setAttribute("category", productCategory);

                if(productCategory != null){
                    List<ProductCategory> listParent = productCategoryService.getAllParent();
                    String parentName="";
                    for(ProductCategory parent : listParent){
                        if(productCategory.getParentCategoryID()==parent.getID()){
                            parentName=parent.getCategoryName();
                        }
                    }
                    request.setAttribute("listParent", listParent);
                    request.setAttribute("parentName", parentName);

                    RequestDispatcher rd = request.getRequestDispatcher("/views/admin/category/editCategory.jsp");
                    rd.forward(request, response);
                }
                else{
                    response.sendRedirect(request.getContextPath() + "/error");
                }

//                catch (Exception e) {
//                    response.sendRedirect(request.getContextPath() + "/error");
//                }
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String categoryName = request.getParameter("name");
        if(productCategory!=null)
        {
            productCategory.setCategoryName(categoryName);
            ProductCategory test=productCategoryService.update(productCategory);
            if(test!=null){
                response.sendRedirect(request.getContextPath() + "/admin-product");
            }
            else {
                response.sendRedirect(request.getContextPath() + "/error");
            }
        }
    }
}
