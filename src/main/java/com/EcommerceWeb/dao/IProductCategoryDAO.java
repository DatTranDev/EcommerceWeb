package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.ProductCategory;

import java.util.List;

public interface IProductCategoryDAO extends GenericDAO<ProductCategory> {
    List<ProductCategory> getAll();
    int add(ProductCategory product);
    void update(ProductCategory product);
    void delete(int id);
}
