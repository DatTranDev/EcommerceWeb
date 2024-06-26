package com.EcommerceWeb.controller.web;

import java.io.IOException;
import java.net.URLEncoder;
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
		} else
			if (action!=null&& action.equals("register")) {
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");
			if(message!=null && alert!=null) {
				request.setAttribute("message", message);
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/register.jsp");
			rd.forward(request, response);
		} else
			if (action!=null && action.equals("forgotpassword")){
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");
			if(message!=null && alert!=null) {
				request.setAttribute("message", message);
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/forgotPassword.jsp");
			rd.forward(request, response);
		}
			else
				if(action!=null && action.equals("resetpassword")) {
				String code = request.getParameter("code");
				SiteUser finded = (SiteUser) SessionUtil.getInstance().getValue(request, "finded");
				String message = request.getParameter("message");
				String alert = request.getParameter("alert");
				if(message!=null && alert!=null) {
					request.setAttribute("message", message);
					request.setAttribute("alert", alert);
				}
				request.setAttribute("code", code);
				request.setAttribute("finded", finded);
				RequestDispatcher rd = request.getRequestDispatcher("/views/resetPassword.jsp");
				rd.forward(request, response);
			}
		else {
			SiteUser firstLoad = (SiteUser) SessionUtil.getInstance().getValue(request, "SITEUSER");;

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
			request.setAttribute("Product", product);
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
			SessionUtil.getInstance().putValue(request, "Product", product);
			SessionUtil.getInstance().putValue(request, "ShoesCategory", shoesCategory);
			SessionUtil.getInstance().putValue(request, "AccessoriesCategory", accessoriesCategory);

			if(firstLoad!=null) {
				request.setAttribute("SITEUSER", firstLoad);
				if(firstLoad.getRole().equals("admin")) {
					RequestDispatcher rd =request.getRequestDispatcher("/views/admin/home.jsp");
					rd.forward(request, response);
				} else if(firstLoad.getRole().equals("Khách hàng")) {
					RequestDispatcher rd =request.getRequestDispatcher("/views/web/home.jsp");
					rd.forward(request, response);
				}
			}else {
				RequestDispatcher rd =request.getRequestDispatcher("/views/web/home.jsp");
				rd.forward(request, response);
			}
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
				String loginUrl = request.getContextPath() + "/dang-nhap?action=login&message=" + URLEncoder.encode("Sai tài khoản hoặc mật khẩu", "UTF-8") + "&alert=danger";
				response.sendRedirect(loginUrl);
			}
		}
		else
			if (action!=null&& action.equals("register")) {
			String username = request.getParameter("DisplayName");
			String password = request.getParameter("Password");
			String email = request.getParameter("Email");
			String repeatPassword = request.getParameter("RepeatPassword");
			if(Helper.checkEmail(email)) {
				SiteUser finded = siteUserService.findByEmail(email);
				if(finded!=null) {
					String loginUrl = request.getContextPath() + "/dang-nhap?action=register&message=" + URLEncoder.encode("Email đã tồn tại", "UTF-8") + "&alert=danger";
					response.sendRedirect(loginUrl);
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
							RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
							request.setAttribute("message", "Đăng ký thành công, vui lòng đăng nhập");
							request.setAttribute("alert", "success");
							rd.forward(request, response);
						} else {
							String loginUrl = request.getContextPath() + "/dang-nhap?action=regíster&message=" + URLEncoder.encode("Lỗi xảy ra, đăng ký thất bại", "UTF-8") + "&alert=danger";
							response.sendRedirect(loginUrl);
						}
					} else {
						String loginUrl = request.getContextPath() + "/dang-nhap?action=register&message=" + URLEncoder.encode("Mật khẩu xác nhận chưa đúng ", "UTF-8") + "&alert=danger";
						response.sendRedirect(loginUrl);
					}
				}

			} else {
				request.setAttribute("message", "Wrong email format");
				RequestDispatcher rd = request.getRequestDispatcher("/views/register.jsp");
				rd.forward(request, response);
			}
		}
		else if (action!=null&& action.equals("forgotpassword")) {
			String email = request.getParameter("email");
			SiteUser finded = siteUserService.findByEmail(email);
			if(finded!=null) {
				String code = Helper.sendEmail(email);
				SessionUtil.getInstance().putValue(request, "code", code);
				SessionUtil.getInstance().putValue(request, "finded", finded);
				response.sendRedirect(request.getContextPath()+"/dang-nhap?action=resetpassword&message=" + URLEncoder.encode("Mã xác nhận đã được gửi đến email của bạn", "UTF-8") + "&alert=success");
			} else {
				String loginUrl = request.getContextPath() + "/dang-nhap?action=forgotpassword&message=" + URLEncoder.encode("Email không tồn tại", "UTF-8") + "&alert=danger";
				response.sendRedirect(loginUrl);
			}
		}
		else if (action!=null && action.equals("resetpassword")){
				String checkcode = (String) SessionUtil.getInstance().getValue(request, "code");
				String code = request.getParameter("codeverify");
				String password = request.getParameter("Password");
				String repeatPassword = request.getParameter("RepeatPassword");
				SiteUser finded = (SiteUser) SessionUtil.getInstance().getValue(request, "finded");
				if(password.equals(repeatPassword)){
					if(checkcode.equals(code)) {
						finded.setPassword(Helper.toMd5(password));
						siteUserService.update(finded);
						SessionUtil.getInstance().removeValue(request, "finded");
						SessionUtil.getInstance().removeValue(request, "code");
						String loginUrl = request.getContextPath() + "/dang-nhap?action=login&message=" + URLEncoder.encode("Đổi mật khẩu thành công", "UTF-8") + "&alert=success";
						response.sendRedirect(loginUrl);

					} else {
						String loginUrl = request.getContextPath() + "/dang-nhap?action=resetpassword&message=" + URLEncoder.encode("Mã xác nhận không đúng", "UTF-8") + "&alert=danger";
						response.sendRedirect(loginUrl);
					}
				}else {
					String loginUrl = request.getContextPath() + "/dang-nhap?action=resetpassword&message=" + URLEncoder.encode("Mật khẩu xác nhận chưa đúng", "UTF-8") + "&alert=danger";
					response.sendRedirect(loginUrl);
				}
			}
	}
}
