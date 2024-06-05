package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IAddressDAO;
import com.EcommerceWeb.mapper.AddressMapper;

import com.EcommerceWeb.model.Address;


import java.util.List;

public class AddressDAO extends AbstractDAO<Address> implements IAddressDAO {
    @Override
    public Address findOne(int addressID) {
        String sql = "select * from Address where ID = ?";
        List<Address> list = query(sql, new AddressMapper(), addressID);
        return list.isEmpty()?null:list.get(0);
    }
}
