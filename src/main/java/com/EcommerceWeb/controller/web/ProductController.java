package com.EcommerceWeb.controller.web;

import com.EcommerceWeb.model.*;
import com.EcommerceWeb.service.IProductCategoryService;
import com.EcommerceWeb.service.IProductConfigService;
import com.EcommerceWeb.service.IProductItemService;
import com.EcommerceWeb.service.IProductService;
import com.EcommerceWeb.service.IUserReviewService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (urlPatterns = {"/products/*"})
public class ProductController extends HttpServlet {
    @Inject
    private IProductService productService;
    @Inject
    private IProductCategoryService productCategoryService;
    @Inject
    private IProductItemService productItemService;
    @Inject
    private IProductConfigService productConfigService;
    @Inject
    private IUserReviewService userReviewService;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String productId = null;

        if (pathInfo != null) {
            String[] pathParts = pathInfo.split("/");
            if (pathParts.length > 1) {
                productId = pathParts[1];
                try {
                    int id = Integer.parseInt(productId);
                    Product product = productService.findOne(id);
                    if (product != null) {
                        product.setCategory(productCategoryService.findOne(product.getCategoryID()));
                        product.setMinPrice(productService.getMinPrice(id));
                        product.setMaxPrice(productService.getMaxPrice(id));
                        request.setAttribute("Product", product);

                        List<ProductCategory> productCategories = productCategoryService.getAll();
                        request.setAttribute("ProductCategory", productCategories);

                        List<Product> productList = productService.getProductByCategory(product.getCategory().getParentCategoryID());
                        for (Product product1 : productList) {
                            product1.setMinPrice(productService.getMinPrice(product1.getID()));
                        }
                        request.setAttribute("ProductList", productList);

                        // Lấy danh sách productItem ứng với productId
                        List<ProductItem> productItemList = productItemService.getProductItemByProductIDForProductDetail(product.getID());
                        if (productItemList == null) {
                            response.sendRedirect(request.getContextPath() + "/error");
                            return;
                        }




                        //test
                        int count=0;
                        for(ProductItem productItem: productItemList){
                            System.out.println("Bat dau: "+count);
                            System.out.println(productItem.getProduct().getDisplayName() );
                            System.out.println("So luong: "+productItem.getQuantityInStock() );
                            for(ProductConfig productConfig: productItem.getListProductConfig()){
                                    System.out.println(productConfig.getVariationOption().getVariation().getDisplayName()+"-"+productConfig.getVariationOption().getValue());
                            }
                            System.out.println("Ket thuc: "+count++ +"---------------------------------------------------------"+"\n");

                        }



                        //end test



                        request.setAttribute("productItemList", productItemList);

                        SiteUser user = (SiteUser) request.getSession().getAttribute("SITEUSER");
                        if(user == null)
                        {
                            request.setAttribute("IsReviewed", true);
                        }
                        else{
                            request.setAttribute("IsReviewed", !userReviewService.checkUserReview(user.getID(), product.getID()));
                            int orderLineID = userReviewService.getOrderLineID(user.getID(), product.getID());
                            request.setAttribute("OrderLineID", orderLineID);
                        }


                        List<UserReview> userReviews = userReviewService.getReviewByProductID(id);
                        request.setAttribute("UserReview", userReviews);
                        float avgRating = 0;
                        if(userReviews!=null) {

                            for (int i = 0; i < userReviews.size(); i++) {
                                avgRating += userReviews.get(i).getRatingValue();
                            }
                            avgRating = avgRating / userReviews.size();

                            avgRating = Math.round(avgRating * 10) / 10.0f;

                            int roundAvg = Math.round(avgRating);



                            request.setAttribute("AvgRating", avgRating);
                            request.setAttribute("CountRating", userReviews.size());
                            request.setAttribute("RoundAvg", roundAvg);
                        }
                        else{
                            request.setAttribute("AvgRating", 0);
                            request.setAttribute("CountRating", 0);
                            request.setAttribute("RoundAvg", 0);
                        }
                        RequestDispatcher rd = request.getRequestDispatcher("/views/web/productDetail.jsp");
                        rd.forward(request, response);
                    } else {
                        response.sendRedirect(request.getContextPath() + "/error");
                    }
                } catch (Exception e) {
                    response.sendRedirect(request.getContextPath() + "/error");
                }
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/product-collections");
        }
    }
}
