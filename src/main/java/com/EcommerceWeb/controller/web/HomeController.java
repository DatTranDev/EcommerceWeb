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
import com.EcommerceWeb.service.IProductService;


@WebServlet(urlPatterns = {"/trang-chu"})
public class HomeController extends HttpServlet{
	
	@Inject
	private IProductService productService;

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("Product", productService.getAll());	
		//request.setAttribute("ProductByCategory", productService.getProductByCategory(1));
		RequestDispatcher rd =request.getRequestDispatcher("/views/web/home.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
}
