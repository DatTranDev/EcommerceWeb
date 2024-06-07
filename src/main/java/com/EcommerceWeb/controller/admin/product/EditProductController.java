package com.EcommerceWeb.controller.admin.product;

import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductCategory;
import com.EcommerceWeb.model.ProductItem;
import com.EcommerceWeb.service.impl.ProductCategoryService;
import com.EcommerceWeb.service.impl.ProductItemService;
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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/admin-editProduct/*"})
public class EditProductController extends HttpServlet {
    @Inject
    private ProductService productService;
    @Inject
    private ProductCategoryService productCategoryService;
    @Inject
    private ProductItemService productItemService;
    @Inject
    private Product selectedProduct;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        List<CategoryShow> listCategoryShow= new ArrayList<>();
        List<ProductItem> listProductItem= new ArrayList<>();
        List<String> listImage= new ArrayList<>();
        String productId = null;
        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1) {
                productId = pathParts[1];

                    int id = Integer.parseInt(productId);
                    Product product = productService.findOne(id);
                    if(product != null){
                        product.setCategory(productCategoryService.findOne(product.getCategoryID()));
                        product.setMinPrice(productService.getMinPrice(id));
                        product.setMaxPrice(productService.getMaxPrice(id));
                        selectedProduct=product;
                        request.setAttribute("product", product);
                        if(!(product.getProductImage()==null) && !product.getProductImage().isEmpty()){
                            listImage= Arrays.stream(product.getProductImage().split(", "))
                                .map(String::trim)
                                    .collect(Collectors.toList());
                        }
                        request.setAttribute("listImage", listImage);

                        //
                        listProductItem= productService.getProductItems(id);
                        request.setAttribute("listProductItem", listProductItem);

                        List<ProductCategory> listCategory = productCategoryService.getAll();
                        for(ProductCategory productCategory:listCategory) {
                            CategoryShow categoryShow = new CategoryShow();
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
                            if(productCategory.getID()==product.getCategory().getID())
                            {
                                listCategoryShow.add(0,categoryShow);
                            }
                            else
                            {
                                listCategoryShow.add(categoryShow);
                            }
                        }
                        request.setAttribute("listCategory", listCategoryShow);



                        RequestDispatcher rd = request.getRequestDispatcher("/views/admin/product/editProduct.jsp");
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
        if(selectedProduct!=null)
        {
            selectedProduct.setProductImage(image);
            selectedProduct.setDisplayName(name);
            selectedProduct.setCategoryID(categoryID);
            selectedProduct.setDescription(description);
            Product test=productService.update(selectedProduct);
            if(test!=null)
            {
                response.sendRedirect(request.getContextPath() + "/admin-product");
            }
        }
        else
        {
            response.sendRedirect(request.getContextPath() + "/error");
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
