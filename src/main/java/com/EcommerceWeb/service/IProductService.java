package com.EcommerceWeb.service;

import java.util.List;

import com.EcommerceWeb.model.Product;

public interface IProductService {
	List<Product> getAll();
	List<Product> getProductByCategory(int id);
	int add(Product product);
	void update(Product product);
	void delete(int id);
	Product findOne(int id);
	int getTotalItem();
}
