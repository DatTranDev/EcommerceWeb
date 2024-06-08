package com.EcommerceWeb.mapper;

import com.EcommerceWeb.model.ShoppingCartModel;

import java.sql.ResultSet;

public class ShoppingCartMapper implements RowMapper<ShoppingCartModel> {
    @Override
    public ShoppingCartModel mapRow(ResultSet rs) {

        ShoppingCartModel shoppingCartModel = new ShoppingCartModel();
        try{
            shoppingCartModel.setID(rs.getInt("ID"));
            shoppingCartModel.setUserID(rs.getInt("UserID"));
            return shoppingCartModel;
        }
        catch (Exception e){

            return null;
        }

    }
}
