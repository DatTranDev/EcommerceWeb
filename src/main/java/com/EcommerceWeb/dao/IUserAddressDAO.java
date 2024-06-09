package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.UserAddress;

import java.util.List;

public interface IUserAddressDAO extends GenericDAO<UserAddress> {
    List<UserAddress> findByUserID(int userID);
    int insert(UserAddress userAddress);

    UserAddress findOneByAddressID(int id);

    UserAddress findOneByAddressIDForDelete(int id);//phuc vu phan xoa
    void update(UserAddress userAddress);
}
