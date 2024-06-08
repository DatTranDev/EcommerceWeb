package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IProductConfigDAO;
import com.EcommerceWeb.mapper.ProductConfigMapper;
import com.EcommerceWeb.mapper.ProductItemMapper;
import com.EcommerceWeb.model.ProductConfig;
import com.EcommerceWeb.model.ProductItem;

import java.util.List;

public class ProductConfigDAO extends AbstractDAO <ProductConfig> implements IProductConfigDAO {
    @Override
    public List<ProductConfig> getAll() {
        String sql = "SELECT * FROM ProductConfig WHERE IsDeleted = 0";
        return query(sql, new ProductConfigMapper());
    }

    @Override
    public List<ProductConfig> getByProductItemID(int id) {
        String sql = "SELECT * FROM ProductItem WHERE ProductItemID = ? and IsDeleted = 0";
        return query(sql, new ProductConfigMapper(),id);
    }

    @Override
    public int add(ProductConfig productConfig) {
        String sql = "INSERT INTO Product (ProductItemID, VariationID) \r\n"
                + "VALUES (?, ?)";
        return insert(sql, productConfig.getProductItemID(), productConfig.getVariationID());
    }

    @Override
    public void delete(int itemID, int optionID) {
        String sql = "UPDATE ProductItem SET IsDeleted = true WHERE ProductItemID = ? and VariationID = ?";
        update(sql, itemID,optionID);
    }

    @Override
    public ProductConfig findOne(int itemID, int optionID) {
        String sql = "SELECT * FROM ProductItem WHERE ProductItemID = ? and VariationID = ?  and IsDeleted = 0";
        List<ProductConfig> productConfigs = query(sql, new ProductConfigMapper(), itemID,optionID);
        return productConfigs.isEmpty() ? null : productConfigs.get(0);
    }

    @Override
    public List<ProductConfig> findByProductItemID(int productItemID) {
        String sql = "SELECT * FROM ProductConfig WHERE ProductItemID = ?  and IsDeleted = 0";
        return query(sql, new ProductConfigMapper(), productItemID);
    }
}
