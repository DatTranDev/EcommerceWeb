package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.IAddressDAO;
import com.EcommerceWeb.dao.IUserAddressDAO;
import com.EcommerceWeb.dao.impl.AddressDAO;
import com.EcommerceWeb.dao.impl.UserAddressDAO;
import com.EcommerceWeb.model.Address;
import com.EcommerceWeb.model.UserAddress;
import com.EcommerceWeb.service.IUserAddressService;

import javax.inject.Inject;
import java.util.ArrayList;
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
        List<UserAddress> listNew =  new ArrayList<>();
        for(UserAddress item:list){
            if(item.isDefault()){
                listNew.add(item);
                break;
            }
        }
        if(!listNew.isEmpty()){
            for(UserAddress item:list) {
                if(item!=listNew.get(0)){
                    listNew.add(item);
                }
            }
            return listNew;
        }
        else{
            return list;

        }
    }

    @Override
    public UserAddress insert(UserAddress userAddress,Address address) {
        int idAddressInsert = addressDAO.insert(address);
        if (idAddressInsert == -1) return null;
        userAddress.setAddressID(idAddressInsert);
         userAddressDAO.insert(userAddress);
        return userAddressDAO.findOneByAddressID(idAddressInsert);
    }

    @Override
    public boolean deleteUserAddress(UserAddress userAddress) {

        userAddress.setDefault(false);
        userAddress.setDeleted(true);

        userAddressDAO.update(userAddress);

        if(userAddressDAO.findOneByAddressIDForDelete(userAddress.getAddressID())==null)
            return false;

        return true;

    }
}