package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.OrderLineModel;

public class OrderLineMapper implements RowMapper<OrderLineModel> {
    @Override
    public OrderLineModel mapRow(ResultSet resultSet) {
        try {
            OrderLineModel orderLine = new OrderLineModel();
            orderLine.setID(resultSet.getInt("ID"));
            orderLine.setProductItemID(resultSet.getInt("ProductItemID"));
            orderLine.setOrderID(resultSet.getInt("OrderID"));
            orderLine.setQuantity(resultSet.getInt("Quantity"));
            orderLine.setPrice(resultSet.getDouble("Price"));
            orderLine.setDeleted(resultSet.getBoolean("IsDeleted"));
            return orderLine;
        } catch (SQLException e) {
            return null;
        }
    }
}

