package com.EcommerceWeb.controller.admin.category;

import com.EcommerceWeb.model.ProductCategory;
import com.EcommerceWeb.service.impl.ProductCategoryService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-addCategory"})
public class AddCategoryController extends HttpServlet {
    @Inject
    private ProductCategoryService productCategoryService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductCategory> listParent = productCategoryService.getAllParent();
        request.setAttribute("listParent", listParent);
        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/category/addCategory.jsp");
        rd.forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        int parentId = Integer.parseInt(request.getParameter("category"));
        ProductCategory newCategory = new ProductCategory();
        newCategory.setCategoryName(name);
        newCategory.setParentCategoryID(parentId);
        ProductCategory test= productCategoryService.add(newCategory);
        if(test!=null){
            String successMessage = "Thêm danh mục thành công";
            String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
            HttpSession session = request.getSession();
            session.setAttribute("alert", encodedMessage);
            response.sendRedirect(request.getContextPath() + "/admin-product");
            return;
        }
        else
        {
            String successMessage = "Thêm thất bại";
            String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
            HttpSession session = request.getSession();
            session.setAttribute("alert", encodedMessage);
            response.sendRedirect(request.getContextPath() + "/admin-addCategory");
            return;
        }
    }
}
