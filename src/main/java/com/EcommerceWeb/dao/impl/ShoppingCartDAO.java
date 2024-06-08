package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IShoppingCartDAO;
import com.EcommerceWeb.mapper.ShoppingCartMapper;
import com.EcommerceWeb.model.ShoppingCartModel;

import java.util.List;

public class ShoppingCartDAO extends AbstractDAO<ShoppingCartModel> implements IShoppingCartDAO
{


    //tim gio hang cua user
    @Override
    public ShoppingCartModel findOneByUserID(int userID) {
        String sql="select * from ShoppingCart where UserID = ?";
        List<ShoppingCartModel> list = query(sql, new ShoppingCartMapper(), userID);

        return list.isEmpty()?null:list.get(0);
    }
}
