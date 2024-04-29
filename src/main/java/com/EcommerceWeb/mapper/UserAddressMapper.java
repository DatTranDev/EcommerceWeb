package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.UserAddress;

public class UserAddressMapper implements RowMapper<UserAddress> {
    @Override
    public UserAddress mapRow(ResultSet resultSet) {
        try {
            UserAddress userAddress = new UserAddress();
            userAddress.setUserID(resultSet.getInt("UserID"));
            userAddress.setAddressID(resultSet.getInt("AddressID"));
            userAddress.setDefault(resultSet.getBoolean("IsDefault"));
            userAddress.setDeleted(resultSet.getBoolean("IsDeleted"));
            return userAddress;
        } catch (SQLException e) {
            return null;
        }
    }
}

