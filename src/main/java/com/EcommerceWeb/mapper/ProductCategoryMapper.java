package com.EcommerceWeb.mapper;

import com.EcommerceWeb.model.ProductCategory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductCategoryMapper implements RowMapper<ProductCategory>{
    @Override
    public ProductCategory mapRow(ResultSet resultSet) {
        try {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setID(resultSet.getInt("ID"));
            productCategory.setParentCategoryID(resultSet.getInt("ParentCategoryID"));
            productCategory.setCategoryName(resultSet.getString("CategoryName"));
            productCategory.setDeleted(resultSet.getBoolean("IsDeleted"));
            return productCategory;
        } catch (SQLException e) {
            return null;
        }
    }

}
