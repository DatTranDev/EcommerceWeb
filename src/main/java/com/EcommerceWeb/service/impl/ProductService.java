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
		return productDAO.getAll();
	}
	@Override
	public List<Product> getProductByCategory(int id){
		return productDAO.getProductByCategory(id);
	}
	@Override
	public int add(Product product) {
		return productDAO.add(product);
	}
	public void update(Product product) {
		productDAO.update(product);
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
}
