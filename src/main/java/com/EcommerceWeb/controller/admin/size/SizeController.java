package com.EcommerceWeb.controller.admin.size;

import com.EcommerceWeb.controller.admin.product.ProductController;
import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductCategory;
import com.EcommerceWeb.model.VariationOption;
import com.EcommerceWeb.service.impl.ProductCategoryService;
import com.EcommerceWeb.service.impl.ProductService;
import com.EcommerceWeb.service.impl.VariationOptionService;

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

@WebServlet(urlPatterns = {"/admin-size"})
public class SizeController extends HttpServlet {
    @Inject
    private VariationOptionService variationOptionService;
    @Inject
    private ProductCategoryService productCategoryService;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<VariationOption> listSize = variationOptionService.findAllSize();
        List<VariationOption> listColor = variationOptionService.findAllColor();
        request.setAttribute("listSize", listSize);
        request.setAttribute("listColor", listColor);

        RequestDispatcher rd = request.getRequestDispatcher("views/admin/size/listSize.jsp");
        rd.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("UTF-8");
//        String list = request.getParameter("listId");
//        if(list!=null && !list.isEmpty())
//        {
//            String[] stringArray = list.split(",\\s*"); // Tách chuỗi bằng dấu phẩy, loại bỏ khoảng trắng dư thừa
//            List<Integer> integerList = new ArrayList<>();
//            for (String numberString : stringArray) {
//                int number = Integer.parseInt(numberString.trim()); // Chuyển chuỗi thành số nguyên
//                integerList.add(number);
//            }
//            if(integerList.size()>0)
//            {
//                for(Integer integer:integerList)
//                {
//                    productService.delete(integer);
//                }
//                response.sendRedirect(request.getContextPath() + "/admin-product");
//            }
//        }
    }
}
