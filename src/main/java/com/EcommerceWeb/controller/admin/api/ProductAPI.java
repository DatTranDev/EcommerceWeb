package com.EcommerceWeb.controller.admin.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.service.IProductService;
import com.EcommerceWeb.utils.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebServlet(urlPatterns = {"/api-admin/product"})
public class ProductAPI extends HttpServlet {

	@Inject
	private IProductService productService;
	
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Product product = HttpUtil.of(request.getReader()).toModel(Product.class);
		product = productService.add(product);
		mapper.writeValue(response.getOutputStream(), product);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Product product = HttpUtil.of(request.getReader()).toModel(Product.class);
		product = productService.update(product);
		mapper.writeValue(response.getOutputStream(), product);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		Product product = HttpUtil.of(request.getReader()).toModel(Product.class);
		productService.delete(product.getID());
		PrintWriter out = response.getWriter();
	    out.print("{ \"message\" : \"Delete successfully\" }");
	    out.flush();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
	}
}
