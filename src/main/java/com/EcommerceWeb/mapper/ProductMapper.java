package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.Product;

public class ProductMapper implements RowMapper<Product>{
	@Override
	public Product mapRow(ResultSet resultSet) {
		try {
			Product product = new Product();
			product.setID(resultSet.getInt("ID"));
			product.setCategoryID(resultSet.getInt("CategoryID"));
			product.setDisplayName(resultSet.getString("DisplayName"));
			product.setDescription(resultSet.getString("Description"));
			product.setProductImage(resultSet.getString("ProductImage"));
			product.setDeleted(resultSet.getBoolean("IsDeleted"));	
			return product;
		} catch (SQLException e) {
			return null;
		}
	}
}
