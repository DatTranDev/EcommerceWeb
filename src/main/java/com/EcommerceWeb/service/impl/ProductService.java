package com.EcommerceWeb.service.impl;
import java.util.List;

import javax.inject.Inject;

import com.EcommerceWeb.dao.IProductDAO;
import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.service.IProductService;

public class ProductService implements IProductService{
	@Inject
	private IProductDAO productDAO;
	
	@Override
	public List<Product> getAll(){
		List<Product> rs = productDAO.getAll();
		return rs;
	}
	@Override
	public List<Product> getProductByCategory(int id){

		return productDAO.getProductByCategory(id);
	}
	@Override
	public Product add(Product product) {
		int productId = productDAO.add(product);
		return productDAO.findOne(productId);
	}
	public Product update(Product product) {
		int id = product.getID();
		productDAO.update(product);
		return productDAO.findOne(id);		
	}
	
	public void delete(int id) {
		productDAO.delete(id);
	};
	
	public Product findOne(int id) {
		return productDAO.findOne(id);
	};
	public int getTotalItem() {
		return productDAO.getTotalItem();
	};
	public double getMinPrice(int id) {
		return productDAO.getMinPrice(id);
	}
	public double getMaxPrice(int id) {
		return productDAO.getMaxPrice(id);
	}
	public List<Product> top3saleProduct() {
		return productDAO.top3saleProduct();
	}
	public int count() {
		return productDAO.count();
	}
}
