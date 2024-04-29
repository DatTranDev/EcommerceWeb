package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.OrderStatus;

public class OrderStatusMapper implements RowMapper<OrderStatus> {
    @Override
    public OrderStatus mapRow(ResultSet resultSet) {
        try {
            OrderStatus orderStatus = new OrderStatus();
            orderStatus.setID(resultSet.getInt("ID"));
            orderStatus.setStatus(resultSet.getString("Status"));
            orderStatus.setDeleted(resultSet.getBoolean("IsDeleted"));
            return orderStatus;
        } catch (SQLException e) {
            return null;
        }
    }
}

