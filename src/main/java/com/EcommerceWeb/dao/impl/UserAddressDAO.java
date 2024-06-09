package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IUserAddressDAO;
import com.EcommerceWeb.mapper.ShoppingCartItemMapper;
import com.EcommerceWeb.mapper.SiteUserMapper;
import com.EcommerceWeb.mapper.UserAddressMapper;
import com.EcommerceWeb.model.UserAddress;
import com.EcommerceWeb.utils.Helper;

import java.util.List;

public class UserAddressDAO extends AbstractDAO<UserAddress> implements IUserAddressDAO {
    @Override
    public List<UserAddress> findByUserID(int userID) {
        String sql = "select * from UserAddress where UserID = ? and IsDeleted = 0";
        return query(sql,new UserAddressMapper(),userID);
    }

    @Override
    public int insert(UserAddress userAddress) {
        String sql = "INSERT INTO UserAddress(UserID, AddressID, IsDefault) VALUES(?, ?, ?)";
        return insert(sql.toString(),  userAddress.getUserID(),userAddress.getAddressID(),false);
    }

    @Override
    public UserAddress findOneByAddressID(int id) {
        String sql = "select * from UserAddress where AddressID = ? and IsDeleted = 0";
        try {
            return query(sql,new UserAddressMapper(),id).get(0);
        } catch (Exception e) {
            return null;
        }
    }
}
