package com.EcommerceWeb.controller.admin;

import com.EcommerceWeb.model.OrderLineModel;
import com.EcommerceWeb.model.ProductItem;
import com.EcommerceWeb.model.ShopOrderModel;
import com.EcommerceWeb.model.SiteUser;
import com.EcommerceWeb.service.IProductItemService;
import com.EcommerceWeb.service.IProductService;
import com.EcommerceWeb.service.IShopOrderService;
import com.EcommerceWeb.utils.SessionUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	@Inject
	private IProductItemService productItemService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SiteUser siteUser = (SiteUser) SessionUtil.getInstance().getValue(request, "SITEUSER");

		if(siteUser == null) {
			response.sendRedirect(request.getContextPath() + "/error");
			return;
		}
		List<ShopOrderModel> successShopOrderList = shopOrderService.findAllByOrderStatus();
		List<ShopOrderModel> successShopOrderList2  = shopOrderService.findAllByOrderStatus();
		double sum = 0;
		if(successShopOrderList != null)
			for(ShopOrderModel shopOrderModel : successShopOrderList){
				sum += shopOrderModel.getOrderTotal();
			}

		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(successShopOrderList);
		request.setAttribute("sum", sum);
		request.setAttribute("successShopOrderList", json);



		//xu ly bieu do tron
		List<ProductItem> productItemList = new ArrayList<>();
		Map<Integer, Integer> productItemCountMap = new HashMap<>();
		for(ShopOrderModel shopOrderModel : successShopOrderList2){
			for(OrderLineModel orderLineModel : shopOrderModel.getListOrderLine()){
				productItemCountMap.merge(orderLineModel.getProductItem().getID(), orderLineModel.getQuantity(), Integer::sum);
			}
		}

		// Sắp xếp map theo giá trị tăng dần và lấy ra 3 giá trị lớn nhất
		List<Map.Entry<Integer, Integer>> sortedEntries = new ArrayList<>(productItemCountMap.entrySet());
		sortedEntries.sort(Map.Entry.comparingByValue());
		ProductItem productItem1 = new ProductItem();
		ProductItem productItem2 = new ProductItem();
		ProductItem productItem3 = new ProductItem();
		for (int i = sortedEntries.size()-1; i>=0; i--) {
			Map.Entry<Integer, Integer> entry = sortedEntries.get(i);
			Integer key = entry.getKey();
			Integer value = entry.getValue();
			if(i==sortedEntries.size()-1){
				productItem1.setQuantityInStock(entry.getValue());
				productItem1.setProductID(entry.getKey());
			}else if(i==sortedEntries.size()-2){
				productItem2.setQuantityInStock(entry.getValue());
				productItem2.setProductID(entry.getKey());
			}else{
				productItem3.setQuantityInStock(entry.getValue());
				productItem3.setProductID(entry.getKey());
			}
		}
		//5,7,1
		List<ProductItem> listProductItem = productItemService.getAllisDelete();
		for(ProductItem productItem : listProductItem){
			if(productItem.getID() == productItem1.getProductID()){
				productItem1.setProduct(productItem.getProduct());
				productItem1.getProduct().setDisplayName(productItem.getProduct().getDisplayName());
			}else if(productItem.getID() == productItem2.getProductID()){
				productItem2.setProduct(productItem.getProduct());
				productItem2.getProduct().setDisplayName(productItem.getProduct().getDisplayName());
			}else if(productItem.getID() == productItem3.getProductID()){
				productItem3.setProduct(productItem.getProduct());
				productItem3.getProduct().setDisplayName(productItem.getProduct().getDisplayName());
			}
		}

		request.setAttribute("top1", productItem1);
		request.setAttribute("top2", productItem2);
		request.setAttribute("top3", productItem3);
		RequestDispatcher rd =request.getRequestDispatcher("/views/admin/home.jsp");
		rd.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
