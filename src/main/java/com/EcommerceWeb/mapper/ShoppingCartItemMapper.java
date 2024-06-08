package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.ShoppingCartItemModel;

public class ShoppingCartItemMapper implements RowMapper<ShoppingCartItemModel> {
    @Override
    public ShoppingCartItemModel mapRow(ResultSet resultSet) {
        try {
            ShoppingCartItemModel shoppingCartItem = new ShoppingCartItemModel();
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

