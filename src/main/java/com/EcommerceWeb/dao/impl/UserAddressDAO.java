package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IUserAddressDAO;
import com.EcommerceWeb.mapper.ShoppingCartItemMapper;
import com.EcommerceWeb.mapper.UserAddressMapper;
import com.EcommerceWeb.model.UserAddress;

import java.util.List;

public class UserAddressDAO extends AbstractDAO<UserAddress> implements IUserAddressDAO {
    @Override
    public List<UserAddress> findByUserID(int userID) {
        String sql = "select * from UserAddress where UserID = ? and IsDeleted = 0";
        return query(sql,new UserAddressMapper(),userID);
    }
}
