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
import com.EcommerceWeb.service.IProductCategoryService;
import com.EcommerceWeb.service.IProductService;
import com.EcommerceWeb.utils.Helper;


@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap"})
public class HomeController extends HttpServlet{
	
	@Inject
	private IProductService productService;
	@Inject
	private IProductCategoryService productCategoryService;
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action!=null&& action.equals("login")) {
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
			return;
		} else if (action!=null&& action.equals("logout")) {

		} else {
			request.setAttribute("ProductCategory", productCategoryService.getAll());
			request.setAttribute("Product", productService.getAll());
			RequestDispatcher rd =request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
