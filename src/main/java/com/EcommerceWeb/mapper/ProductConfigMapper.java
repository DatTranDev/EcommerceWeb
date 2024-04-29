package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.ProductConfig;

public class ProductConfigMapper implements RowMapper<ProductConfig> {
    @Override
    public ProductConfig mapRow(ResultSet resultSet) {
        try {
            ProductConfig productConfig = new ProductConfig();
            productConfig.setProductItemID(resultSet.getInt("ProductItemID"));
            productConfig.setVariationID(resultSet.getInt("VariationID"));
            productConfig.setDeleted(resultSet.getBoolean("IsDeleted"));
            return productConfig;
        } catch (SQLException e) {
            return null;
        }
    }
}

