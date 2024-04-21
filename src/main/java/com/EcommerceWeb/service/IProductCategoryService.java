package com.EcommerceWeb.service;

import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductCategory;

import java.util.List;

public interface IProductCategoryService {
    List<ProductCategory> getAll();
    ProductCategory add(ProductCategory productCategory);
    ProductCategory update(ProductCategory productCategory);
    void delete(int id);
    ProductCategory findOne(int id);
}
