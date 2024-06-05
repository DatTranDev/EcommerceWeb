package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.IAddressDAO;
import com.EcommerceWeb.dao.IUserAddressDAO;
import com.EcommerceWeb.dao.impl.AddressDAO;
import com.EcommerceWeb.dao.impl.UserAddressDAO;
import com.EcommerceWeb.model.Address;
import com.EcommerceWeb.model.UserAddress;
import com.EcommerceWeb.service.IUserAddressService;

import javax.inject.Inject;
import java.util.List;

public class UserAddressService implements IUserAddressService {
    @Inject
    IUserAddressDAO userAddressDAO;
    @Inject
    IAddressDAO addressDAO;
    @Override
    public List<UserAddress> findByUserID(int userID) {
        List<UserAddress> list=userAddressDAO.findByUserID(userID);
        if(list==null)return null;

        for(UserAddress item:list){
            Address address =  addressDAO.findOne(item.getAddressID());
            item.setAddress(address);
        }
        return list;
    }
}
