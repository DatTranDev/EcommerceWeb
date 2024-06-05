package com.EcommerceWeb.service;

import com.EcommerceWeb.model.UserAddress;

import java.util.List;

public interface IUserAddressService {
    List<UserAddress> findByUserID(int userID);
}
