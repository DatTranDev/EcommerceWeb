package com.EcommerceWeb.controller.admin.product;

import com.EcommerceWeb.model.*;
import com.EcommerceWeb.service.impl.*;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/admin-editProductItem/*"})
public class EditProductItemController extends HttpServlet {
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
    private ProductItem productItem;
    private int sizeDefault = -1;
    private int colorDefault = -1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        List<String> listImage = new ArrayList<>();
        sizeDefault = -1;
        colorDefault = -1;
        String productId = null;
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1) {
                productId = pathParts[1];
                int id = Integer.parseInt(productId);
                productItem = productItemService.findOne(id);
                if (productItem != null) {
                    request.setAttribute("productItem", productItem);
                    if (!(productItem.getProductImage() == null) && !productItem.getProductImage().isEmpty()) {
                        listImage = Arrays.stream(productItem.getProductImage().split(", "))
                                .map(String::trim)
                                .collect(Collectors.toList());
                    }
                    request.setAttribute("listImage", listImage);
                }
                //
                List<ProductConfig> listVariation = productConfigService.getByProductItemID(productItem.getID());
                for (ProductConfig productConfig : listVariation) {
                    VariationOption variationOption = variationOptionService.findOne(productConfig.getVariationID());
                    if (variationOption != null) {
                        if (variationOption.getVariationID() == 1) {
                            sizeDefault = productConfig.getVariationID();
                        } else {
                            colorDefault = productConfig.getVariationID();
                        }
                    }
                }
                request.setAttribute("size", sizeDefault);
                request.setAttribute("color", colorDefault);

                Product product = productService.findOne(productItem.getProductID());
                if (product != null) {
                    product.setCategory(productCategoryService.findOne(product.getCategoryID()));
                    ProductCategory parentCategory = productCategoryService.findOne(product.getCategory().getParentCategoryID());
                    List<VariationOption> listSize = variationOptionService.findAllSize();
                    List<VariationOption> listColor = variationOptionService.findAllColor();
                    request.setAttribute("listSize", listSize);
                    request.setAttribute("listColor", listColor);
                    RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product/editProductItem.jsp");
                    rd.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/error");
                }
            }

//                catch (Exception e) {
//                    response.sendRedirect(request.getContextPath() + "/error");
//                }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int color = Integer.parseInt(request.getParameter("color"));
        int size = Integer.parseInt(request.getParameter("size"));
        if (size == -1 && color == -1) {
            response.sendRedirect(request.getContextPath() + "/error");
            return;
        }
        Product product = productService.findOne(productItem.getProductID());
        if (product != null) {
            ProductItem check = new ProductItem();
            check = null;
            if (colorDefault != color || sizeDefault != size) {
                check = productService.findItemByVariation(product.getID(), size, color);
            }
            if (check != null) {
                String successMessage = "Mã sản phẩm đã tồn tại";
                String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
                HttpSession session = request.getSession();
                session.setAttribute("alert", encodedMessage);
                response.sendRedirect(request.getContextPath() + "/admin-editProductItem/"+productItem.getID());
                return;
            } else {
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
                newProductItem.setID(productItem.getID());
                newProductItem.setProductID(product.getID());
                newProductItem.setProductImage(image);
                newProductItem.setPrice(price);
                newProductItem.setSKU(name);
                newProductItem.setQuantityInStock(quantity);
                //
                try {
                    ProductItem test = productItemService.update(newProductItem);
                    if (sizeDefault != size) {
                        try {

                            ProductConfig oldSize = new ProductConfig();
                            oldSize.setProductItemID(test.getID());
                            oldSize.setVariationID(sizeDefault);
                            productConfigService.updateVariation(oldSize, size);

                        } catch (Exception e) {
                            String successMessage = "Sửa thất bại";
                            String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
                            HttpSession session = request.getSession();
                            session.setAttribute("alert", encodedMessage);
                            response.sendRedirect(request.getContextPath() + "/admin-editProductItem/"+productItem.getID());
                            return;
                        }
                    }
                    if (colorDefault != color) {
                        try {
                            ProductConfig oldColor = new ProductConfig();
                            oldColor.setProductItemID(test.getID());
                            oldColor.setVariationID(colorDefault);
                            productConfigService.updateVariation(oldColor, color);
                        } catch (Exception e) {
                            String successMessage = "Sửa thất bại";
                            String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
                            HttpSession session = request.getSession();
                            session.setAttribute("alert", encodedMessage);
                            response.sendRedirect(request.getContextPath() + "/admin-editProductItem/"+productItem.getID());
                            return;
                        }
                    }
                } catch (Exception e) {
                    String successMessage = "Sửa thất bại";
                    String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
                    HttpSession session = request.getSession();
                    session.setAttribute("alert", encodedMessage);
                    response.sendRedirect(request.getContextPath() + "/admin-editProductItem/"+productItem.getID());
                    return;
                }
            }
            String successMessage = "Sửa thành công";
            String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
            HttpSession session = request.getSession();
            session.setAttribute("alert", encodedMessage);
            response.sendRedirect(request.getContextPath() + "/admin-editProduct/" + product.getID());
            return;
        }
        else
        {
            String successMessage = "Đã xảy ra lỗi";
            String encodedMessage = URLEncoder.encode(successMessage, "UTF-8");
            HttpSession session = request.getSession();
            session.setAttribute("alert", encodedMessage);
            response.sendRedirect(request.getContextPath() + "/admin-product");
            return;
        }


    }

    public static class ProductShow {
        private int id;
        private String name;
        private int quantity;
        private String category;

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
