package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.ProductItem;

public class ProductItemMapper implements RowMapper<ProductItem> {
    @Override
    public ProductItem mapRow(ResultSet resultSet) {
        try {
            ProductItem productItem = new ProductItem();
            productItem.setID(resultSet.getInt("ID"));
            productItem.setProductID(resultSet.getInt("ProductID"));
            productItem.setSKU(resultSet.getString("SKU"));
            productItem.setQuantityInStock(resultSet.getInt("QuantityInStock"));
            productItem.setProductImage(resultSet.getString("ProductImage"));
            productItem.setPrice(resultSet.getDouble("Price"));
            productItem.setDeleted(resultSet.getBoolean("IsDeleted"));
            return productItem;
        } catch (SQLException e) {
            return null;
        }
    }
}
