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

@WebServlet(urlPatterns = {"/admin-editSize/*"})
public class EditSizeController extends HttpServlet {
    @Inject
    private VariationOptionService variationOptionService;
    @Inject
    private ProductCategoryService productCategoryService;
    private int type=0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        int id;
        type=0;
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1) {
                id=Integer.parseInt(pathParts[1]);
               VariationOption variationOption= variationOptionService.findOne(id);
                if(variationOption!=null){
                    request.setAttribute("variationOption",variationOption);
                    if(variationOption.getVariationID()==1)
                    {
                        type=1;
                    }

                }
            }
        }
        request.setAttribute("type", type);
        List<VariationOption> listSize = variationOptionService.findAllSize();
        List<VariationOption> listColor = variationOptionService.findAllColor();
        request.setAttribute("listSize", listSize);
        request.setAttribute("listColor", listColor);

        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/size/editSize.jsp");
        rd.forward(request,response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String pathInfo = request.getPathInfo();
        int id=0;
        type=0;
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1) {
                id=Integer.parseInt(pathParts[1]);
                VariationOption variationOption= variationOptionService.findOne(id);
                if(variationOption!=null){
                    if(variationOption.getVariationID()==1)
                    {
                        type=1;
                    }

                }
            }
        }
        String name = request.getParameter("name");

        VariationOption newVariationOption=new VariationOption();
        newVariationOption.setID(id);
        newVariationOption.setValue(name);
        try
        {
            variationOptionService.update(newVariationOption);
            response.sendRedirect(request.getContextPath() + "/admin-size");
        }
        catch (Exception e)
        {
            response.sendRedirect(request.getContextPath() + "/error");
        }

        return;

    }
}
