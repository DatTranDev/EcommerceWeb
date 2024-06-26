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
	int getTotalQuantityInStock(int id);
	ProductCategory getCategory(int id);
	List<ProductItem> getProductItems(int id);
	List<Product> top3saleProduct();
	int count();
	ProductItem findItemByVariation(int id, int size, int color);
	ProductItem findItemByOneVariation(int id, int size, int color);
}
