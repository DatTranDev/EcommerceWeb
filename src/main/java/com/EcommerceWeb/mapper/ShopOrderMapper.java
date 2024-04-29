package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.ShopOrder;

public class ShopOrderMapper implements RowMapper<ShopOrder> {
    @Override
    public ShopOrder mapRow(ResultSet resultSet) {
        try {
            ShopOrder shopOrder = new ShopOrder();
            shopOrder.setID(resultSet.getInt("ID"));
            shopOrder.setUserID(resultSet.getInt("UserID"));
            shopOrder.setOrderDate(resultSet.getDate("OrderDate"));
            shopOrder.setPaymentMethodID(resultSet.getInt("PaymentMethodID"));
            shopOrder.setShippingAddressID(resultSet.getInt("ShippingAddressID"));
            shopOrder.setShippingMethodID(resultSet.getInt("ShippingMethodID"));
            shopOrder.setOrderTotal(resultSet.getDouble("OrderTotal"));
            shopOrder.setOrderStatusID(resultSet.getInt("OrderStatusID"));
            shopOrder.setDescription(resultSet.getString("Description"));
            shopOrder.setDeleted(resultSet.getBoolean("IsDeleted"));
            return shopOrder;
        } catch (SQLException e) {
            return null;
        }
    }
}

