package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.UserAddress;

import java.util.List;

public interface IUserAddressDAO extends GenericDAO<UserAddress> {
    List<UserAddress> findByUserID(int userID);
}
