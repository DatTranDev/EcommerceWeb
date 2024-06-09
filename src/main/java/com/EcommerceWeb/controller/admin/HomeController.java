package com.EcommerceWeb.controller.admin;

import com.EcommerceWeb.model.ShopOrderModel;
import com.EcommerceWeb.service.IShopOrderService;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/admin-home"})
public class HomeController extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private IShopOrderService shopOrderService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		List<ShopOrderModel> successShopOrderList = shopOrderService.findAllByOrderStatus();
//		request.setAttribute("successShopOrderList", successShopOrderList);


		RequestDispatcher rd =request.getRequestDispatcher("/views/admin/home.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
