package com.EcommerceWeb.dao;

import com.EcommerceWeb.dao.impl.AddressDAO;
import com.EcommerceWeb.model.Address;

public interface IAddressDAO extends GenericDAO<Address> {
    Address findOne(int addressID);
    int insert(Address address);

}
