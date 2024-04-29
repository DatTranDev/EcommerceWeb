package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.ShoppingCartItem;

public class ShoppingCartItemMapper implements RowMapper<ShoppingCartItem> {
    @Override
    public ShoppingCartItem mapRow(ResultSet resultSet) {
        try {
            ShoppingCartItem shoppingCartItem = new ShoppingCartItem();
            shoppingCartItem.setID(resultSet.getInt("ID"));
            shoppingCartItem.setCartID(resultSet.getInt("CartID"));
            shoppingCartItem.setProductItemID(resultSet.getInt("ProductItemID"));
            shoppingCartItem.setQuantity(resultSet.getInt("Quantity"));
            shoppingCartItem.setDeleted(resultSet.getBoolean("IsDeleted"));
            return shoppingCartItem;
        } catch (SQLException e) {
            return null;
        }
    }
}

