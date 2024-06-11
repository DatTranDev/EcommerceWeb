package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.IProductConfigDAO;
import com.EcommerceWeb.model.ProductConfig;
import com.EcommerceWeb.service.IProductConfigService;
import com.EcommerceWeb.service.IVariationOptionService;

import javax.inject.Inject;
import java.util.List;

public class ProductConfigService implements IProductConfigService {

    @Inject
    private IProductConfigDAO productConfigDAO;

    @Inject
    private IVariationOptionService variationOptionService;

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
    public void updateVariation(ProductConfig old, int idVariation) {
        productConfigDAO.updateVariation(old, idVariation);
    }

    @Override
    public void delete(int itemID, int optionID) {
        productConfigDAO.delete(itemID, optionID);
    }

    @Override
    public ProductConfig findOne(int itemID, int optionID) {
        return productConfigDAO.findOne(itemID, optionID);
    }

    @Override
    public List<ProductConfig> findByProductItemID(int productItemID) {

        List<ProductConfig> list = productConfigDAO.findByProductItemID(productItemID);
        if(list==null)return null;

        for(ProductConfig item: list) {
            item.setVariationOption(variationOptionService.findOne(item.getVariationID()));
        }
        return list;
    }
}
