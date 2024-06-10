package com.EcommerceWeb.controller.admin;

import com.EcommerceWeb.model.ShopOrderModel;
import com.EcommerceWeb.service.IShopOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

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

		List<ShopOrderModel> successShopOrderList = shopOrderService.findAllByOrderStatus();
		double sum = 0;
		if(successShopOrderList != null)
			for(ShopOrderModel shopOrderModel : successShopOrderList){
				sum += shopOrderModel.getOrderTotal();
			}

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(successShopOrderList);
		request.setAttribute("sum", sum);
		request.setAttribute("successShopOrderList", json);


		RequestDispatcher rd =request.getRequestDispatcher("/views/admin/home.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
