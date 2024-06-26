package com.EcommerceWeb.service;

import com.EcommerceWeb.model.ProductConfig;

import java.util.List;

public interface IProductConfigService {
    List<ProductConfig> getAll();
    List<ProductConfig> getByProductItemID(int id);
    int add(ProductConfig productConfig);
    void updateVariation(ProductConfig old, int idVariation);
    void delete(int itemID, int optionID);
    ProductConfig findOne(int itemID, int optionID);
    List<ProductConfig>findByProductItemID(int productItemID);
}
