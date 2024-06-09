package com.EcommerceWeb.service;

import java.util.List;

import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductItem;

public interface IProductService {
	List<Product> getAll();
	List<Product> getProductByCategory(int id);
	Product add(Product product);
	Product update(Product product);
	void delete(int id);
	Product findOne(int id);
	int getTotalItem();
	double getMinPrice(int id);
	double getMaxPrice(int id);
	List<ProductItem> getProductItems(int id);
	List<Product> top3saleProduct();
	int count();
	ProductItem findItemByVariation(int id, int size, int color);
	ProductItem findItemByOneVariation(int id, int size, int color);
}
