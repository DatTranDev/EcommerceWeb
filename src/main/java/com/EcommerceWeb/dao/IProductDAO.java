package com.EcommerceWeb.dao;

import java.util.List;

import com.EcommerceWeb.model.*;

public interface IProductDAO extends GenericDAO<Product> {
	List<Product> getAll();
	List<Product> getProductByCategory(int id);
	int add(Product product);
	void update(Product product);
	void delete(int id);
	Product findOne(int id);
	int getTotalItem();
	double getMinPrice(int id);
	double getMaxPrice(int id);
	List<Product> top3saleProduct();
	int count();
}
