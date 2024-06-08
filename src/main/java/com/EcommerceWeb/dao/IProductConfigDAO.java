package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.ProductConfig;
import com.EcommerceWeb.model.ProductItem;

import java.util.List;

public interface IProductConfigDAO extends GenericDAO<ProductConfig> {
    List<ProductConfig> getAll();
    List<ProductConfig> getByProductItemID(int id);
    int add(ProductConfig productConfig);
    void delete(int itemID, int optionID);
    ProductConfig findOne(int itemID, int optionID);
    List<ProductConfig>findByProductItemID(int productItemID);
}
