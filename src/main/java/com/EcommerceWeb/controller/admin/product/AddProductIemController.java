package com.EcommerceWeb.controller.admin.product;

import com.EcommerceWeb.model.*;
import com.EcommerceWeb.service.impl.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.servlet.HttpConstraintElement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
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
    @Inject
    private VariationOptionService variationOptionService;
    @Inject
    private ProductConfigService productConfigService;
    @Inject
    private Product product;
    @Inject
    ProductCategory parentCategory;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String pathInfo = request.getPathInfo();
            String productId = null;
            if (pathInfo != null) {
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length > 1) {
                    productId = pathParts[1];
                    int id = Integer.parseInt(productId);
                    product = productService.findOne(id);
                    if (product != null) {
                        product.setCategory(productCategoryService.findOne(product.getCategoryID()));
                        parentCategory = productCategoryService.findOne(product.getCategory().getParentCategoryID());

                        List<VariationOption> listSize = variationOptionService.findAllSize();
                        List<VariationOption> listColor = variationOptionService.findAllColor();
                        request.setAttribute("listSize", listSize);
                        request.setAttribute("listColor", listColor);
                        request.setAttribute("product", product);
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
        request.setCharacterEncoding("UTF-8");
        int color = Integer.parseInt(request.getParameter("color"));
        int size = Integer.parseInt(request.getParameter("size"));
        if (product != null) {
                ProductItem check = productService.findItemByVariation(product.getID(), size, color);
                if(check!=null)
                {
                    String successMessage = "Mã sản phẩm đã tồn tại";
                    String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
                    HttpSession session = request.getSession();
                    session.setAttribute("alert", encodedMessage);
                    response.sendRedirect(request.getContextPath() + "/admin-addProductItem/"+product.getID());
                    return;
                }

                String name = request.getParameter("name");
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                double price = Double.parseDouble(request.getParameter("price"));
                String jsonImage = request.getParameter("listImage");
                List<String> listImage = new ArrayList<>();
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    listImage = objectMapper.readValue(jsonImage, new TypeReference<List<String>>() {
                    });
                } catch (Exception e) {
                    response.sendRedirect(request.getContextPath() + "/error");
                }
                String image = String.join(", ", listImage);
                ProductItem newProductItem = new ProductItem();
                newProductItem.setProductID(product.getID());
                newProductItem.setProductImage(image);
                newProductItem.setPrice(price);
                newProductItem.setSKU(name);
                newProductItem.setQuantityInStock(quantity);
                int test = productItemService.add(newProductItem);
                if (test != -1) {
                    ProductConfig newSize= new ProductConfig();
                    newSize.setProductItemID(test);
                    newSize.setVariationID(size);
                    ProductConfig newColor= new ProductConfig();
                    newColor.setProductItemID(test);
                    newColor.setVariationID(color);
                    try
                    {
                        int test2= productConfigService.add(newSize);
                        try {
                            int test3 = productConfigService.add(newColor);
                            String successMessage = "Thêm thành công";
                            String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
                            HttpSession session = request.getSession();
                            session.setAttribute("alert", encodedMessage);
                            response.sendRedirect(request.getContextPath() + "/admin-editProduct/" + product.getID());
                        }
                        catch (Exception e)
                        {
                            productConfigService.delete(test,size);
                            productItemService.delete(test);
                            String successMessage = "Thêm thất bại";
                            String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
                            HttpSession session = request.getSession();
                            session.setAttribute("alert", encodedMessage);
                            response.sendRedirect(request.getContextPath() + "/admin-editProduct/" + product.getID());
                            return;
                        }
                    }
                    catch (Exception e)
                    {
                        productItemService.delete(test);
                        String successMessage = "Thêm thất bại";
                        String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
                        HttpSession session = request.getSession();
                        session.setAttribute("alert", encodedMessage);
                        response.sendRedirect(request.getContextPath() + "/admin-editProduct/" + product.getID());
                    }
                } else {
                    String successMessage = "Thêm thất bại";
                    String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
                    HttpSession session = request.getSession();
                    session.setAttribute("alert", encodedMessage);
                    response.sendRedirect(request.getContextPath() + "/admin-editProduct/" + product.getID());
                    return;
                }

        }

    }
}
