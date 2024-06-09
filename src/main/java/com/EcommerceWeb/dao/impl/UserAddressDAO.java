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

    @Override
    public UserAddress findOneByAddressIDForDelete(int id) {
        String sql = "select * from UserAddress where AddressID = ? and IsDeleted = 1";
        try {
            return query(sql,new UserAddressMapper(),id).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void update(UserAddress userAddress) {
        StringBuilder sql = new StringBuilder("UPDATE UserAddress SET UserID = ?, AddressID = ?, IsDefault = ?, IsDeleted = ?");
        sql.append(" WHERE AddressID = ?");
        update(sql.toString(), userAddress.getUserID(),userAddress.getAddressID(),userAddress.isDefault(),userAddress.isDeleted(),userAddress.getAddressID());
    }
}
