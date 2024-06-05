package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.Address;

public interface IAddressDAO extends GenericDAO<Address> {
    Address findOne(int addressID);

}
