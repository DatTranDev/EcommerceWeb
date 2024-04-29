package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.Address;

public class AddressMapper implements RowMapper<Address> {
    @Override
    public Address mapRow(ResultSet resultSet) {
        try {
            Address address = new Address();
            address.setID(resultSet.getInt("ID"));
            address.setValue(resultSet.getString("Value"));
            return address;
        } catch (SQLException e) {
            return null;
        }
    }
}


