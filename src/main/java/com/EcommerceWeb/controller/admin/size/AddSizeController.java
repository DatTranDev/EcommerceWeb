package com.EcommerceWeb.controller.admin.size;

import com.EcommerceWeb.model.VariationOption;
import com.EcommerceWeb.service.impl.ProductCategoryService;
import com.EcommerceWeb.service.impl.VariationOptionService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/admin-addSize/*"})
public class AddSizeController  extends HttpServlet {
    @Inject
    private VariationOptionService variationOptionService;
    @Inject
    private ProductCategoryService productCategoryService;
    private int type=0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        type=0;
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1) {
                if(pathParts[1].equals("size")) {
                    type=1;
                }
            }
        }
        request.setAttribute("type", type);
        List<VariationOption> listSize = variationOptionService.findAllSize();
        List<VariationOption> listColor = variationOptionService.findAllColor();
        request.setAttribute("listSize", listSize);
        request.setAttribute("listColor", listColor);

        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/size/addSize.jsp");
        rd.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();
        type=0;
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1) {
                if(pathParts[1].equals("size")) {
                    type=1;
                }
            }
        }
        String name = request.getParameter("name");

            VariationOption newVariationOption=new VariationOption();
            if(type==1)
            {
                newVariationOption.setVariationID(1);
            }
            else
            {
                newVariationOption.setVariationID(2);
            }
            newVariationOption.setValue(name);
            int test= variationOptionService.add(newVariationOption);
            if(test!=-1)
            {
                response.sendRedirect(request.getContextPath() + "/admin-size");
            }
            else {
                response.sendRedirect(request.getContextPath() + "/error");
            }
        return;

    }
}
