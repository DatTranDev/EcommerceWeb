package com.EcommerceWeb.controller.web;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.service.IProductCategoryService;
import com.EcommerceWeb.service.IProductService;
import com.EcommerceWeb.service.ISiteUserService;
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
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action!=null&& action.equals("login")) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
			return;
		} else if (action!=null&& action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "SITEUSER");
			response.sendRedirect(request.getContextPath()+"/trang-chu");
			return;
		} else if (action!=null&& action.equals("register")) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/register.jsp");
			rd.forward(request, response);
			return;
		}
		else {
			request.setAttribute("ProductCategory", productCategoryService.getAll());
			request.setAttribute("ShoesCategory", productCategoryService.getByParentCategoryID(1));
			request.setAttribute("AccessoriesCategory", productCategoryService.getByParentCategoryID(2));
			request.setAttribute("Product", productService.getAll());
			RequestDispatcher rd =request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
				request.setAttribute("message", "Login fail");
				RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
				rd.forward(request, response);
			}
		} else if (action!=null&& action.equals("register")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String fullname = request.getParameter("fullname");
			if(Helper.checkEmail(email) && Helper.checkPhone(phone)) {
				request.setAttribute("message", "Register success");
				RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("message", "Register fail");
				RequestDispatcher rd = request.getRequestDispatcher("/views/register.jsp");
				rd.forward(request, response);
			}
		}
	}
}
