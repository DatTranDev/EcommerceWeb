package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.ShippingMethod;

public class ShippingMethodMapper implements RowMapper<ShippingMethod> {
    @Override
    public ShippingMethod mapRow(ResultSet resultSet) {
        try {
            ShippingMethod shippingMethod = new ShippingMethod();
            shippingMethod.setID(resultSet.getInt("ID"));
            shippingMethod.setDisplayName(resultSet.getString("DisplayName"));
            shippingMethod.setPrice(resultSet.getDouble("Price"));
            shippingMethod.setDeleted(resultSet.getBoolean("IsDeleted"));
            return shippingMethod;
        } catch (SQLException e) {
            return null;
        }
    }
}

