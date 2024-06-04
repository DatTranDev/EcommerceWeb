package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.IProductConfigDAO;
import com.EcommerceWeb.model.ProductConfig;
import com.EcommerceWeb.service.IProductConfigService;

import java.util.List;

public class ProductConfigService implements IProductConfigService {
    private IProductConfigDAO productConfigDAO;

    @Override
    public List<ProductConfig> getAll() {
        return productConfigDAO.getAll();
    }

    @Override
    public List<ProductConfig> getByProductItemID(int id) {
        return productConfigDAO.getByProductItemID(id);
    }

    @Override
    public int add(ProductConfig productConfig) {
        return productConfigDAO.add(productConfig);
    }

    @Override
    public void delete(int itemID, int optionID) {
        productConfigDAO.delete(itemID, optionID);
    }

    @Override
    public ProductConfig findOne(int itemID, int optionID) {
        return productConfigDAO.findOne(itemID, optionID);
    }
}
