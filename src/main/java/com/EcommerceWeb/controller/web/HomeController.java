package com.EcommerceWeb.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductCategory;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.model.UserReview;
import com.EcommerceWeb.service.IProductCategoryService;
import com.EcommerceWeb.service.IProductService;
import com.EcommerceWeb.service.ISiteUserService;
import com.EcommerceWeb.service.IUserReviewService;
import com.EcommerceWeb.utils.FormUtil;
import com.EcommerceWeb.utils.Helper;
import com.EcommerceWeb.utils.SessionUtil;


@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat"})
public class HomeController extends HttpServlet{
	
	@Inject
	private IProductService productService;
	@Inject
	private IProductCategoryService productCategoryService;
	@Inject
	private ISiteUserService siteUserService;
	@Inject
	private IUserReviewService userReviewService;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		if(action!=null&& action.equals("login")) {
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");
			if(message!=null && alert!=null) {
				request.setAttribute("message", message);
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
			return;
		} else if (action!=null&& action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "SITEUSER");
			response.sendRedirect(request.getContextPath()+"/trang-chu");
		} else if (action!=null&& action.equals("register")) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/register.jsp");
			rd.forward(request, response);
		} else if (action!=null && action.equals("forgotpassword")){
			RequestDispatcher rd = request.getRequestDispatcher("/views/forgotPassword.jsp");
			rd.forward(request, response);
		}
		else {
			List<ProductCategory> productCategory = productCategoryService.getAll();
			List<Product> product = productService.getAll();
			for(Product prd : product){
				prd.setMinPrice(productService.getMinPrice(prd.getID()));
			}
			List<ProductCategory> shoesCategory = productCategoryService.getByParentCategoryID(1);
			List<ProductCategory> accessoriesCategory = productCategoryService.getByParentCategoryID(2);
			request.setAttribute("ProductCategory", productCategory);
			request.setAttribute("ShoesCategory", shoesCategory);
			request.setAttribute("AccessoriesCategory", accessoriesCategory);
			//request.setAttribute("Product", product);
			List<Product> firstEightProducts = new ArrayList<>();
			if (product.size() >= 8) {
				firstEightProducts = product.subList(0, 8);
			} else {
				firstEightProducts = product;
			}
			request.setAttribute("ProductLimitEight", firstEightProducts);
			List<Product> top3SaleProduct = productService.top3saleProduct();
			if (top3SaleProduct == null)	 {
				top3SaleProduct = product.subList(0, 3);
			}
			else
				if (top3SaleProduct.size() <3) {
					top3SaleProduct = product.subList(0, 3);
				}
				else {
					for(Product prd : top3SaleProduct){
						prd.setMinPrice(productService.getMinPrice(prd.getID()));
					}
				}
			request.setAttribute("Top3SaleProduct", top3SaleProduct);
			int productCount = productService.count();
			request.setAttribute("ProductCount", productCount);
			int userCount = siteUserService.count();
			request.setAttribute("UserCount", userCount);
			int reviewCount = userReviewService.count();
			request.setAttribute("ReviewCount", reviewCount);
			List<UserReview> goodReview = userReviewService.getGoodReview();
			request.setAttribute("GoodReview", goodReview);
			SessionUtil.getInstance().putValue(request, "ProductCategory", productCategory);
			//SessionUtil.getInstance().putValue(request, "Product", product);
			SessionUtil.getInstance().putValue(request, "ShoesCategory", shoesCategory);
			SessionUtil.getInstance().putValue(request, "AccessoriesCategory", accessoriesCategory);

			RequestDispatcher rd =request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if(action!=null&&action.equals("login")) {
			SiteUser model = FormUtil.toModel(SiteUser.class, request);
			model = siteUserService.findByUserNameAndPassword(model.getEmail(), model.getPassword());
			if(model!=null) {
				SessionUtil.getInstance().putValue(request, "SITEUSER", model);
				if(model.getRole().equals("admin")) {
					response.sendRedirect(request.getContextPath()+"/admin-home");
				} else if(model.getRole().equals("Khách hàng")) {
					response.sendRedirect(request.getContextPath()+"/trang-chu");
				}
			} else {
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=Tài khoản hoặc mật khẩu không đúng&alert=danger");
			}
		} else if (action!=null&& action.equals("register")) {
			String username = request.getParameter("DisplayName");
			String password = request.getParameter("Password");
			String email = request.getParameter("Email");
			String repeatPassword = request.getParameter("RepeatPassword");
			if(Helper.checkEmail(email)) {
				SiteUser finded = siteUserService.findByEmail(email);
				if(finded!=null) {
					response.sendRedirect(request.getContextPath()+"/dang-nhap?action=register&message=Email already exist&alert=danger");
					return;
				}
				else{
					if(password.equals(repeatPassword)) {
						SiteUser siteUser = new SiteUser();
						siteUser.setDisplayName(username);
						siteUser.setPassword(password);
						siteUser.setEmail(email);
						siteUser.setRole("Khách hàng");
						siteUser.setDeleted(false);
						int result = siteUserService.register(siteUser);
						if(result>0) {
							response.sendRedirect(request.getContextPath()+"/dang-nhap?action=login&message=Register success&alert=success");
							//RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
							//rd.forward(request, response);
						} else {
							response.sendRedirect(request.getContextPath()+"/dang-nhap?action=register&message=Register fail&alert=danger");
						}
					} else {
						response.sendRedirect(request.getContextPath()+"/dang-nhap?action=register&message=Password not match&alert=danger");
					}
				}

			} else {
				request.setAttribute("message", "Wrong email format");
				RequestDispatcher rd = request.getRequestDispatcher("/views/register.jsp");
				rd.forward(request, response);
			}
		}
	}
}
