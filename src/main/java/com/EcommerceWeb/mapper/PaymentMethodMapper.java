package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.PaymentMethod;

public class PaymentMethodMapper implements RowMapper<PaymentMethod> {
    @Override
    public PaymentMethod mapRow(ResultSet resultSet) {
        try {
            PaymentMethod paymentMethod = new PaymentMethod();
            paymentMethod.setID(resultSet.getInt("ID"));
            paymentMethod.setDisplayName(resultSet.getString("DisplayName"));
            paymentMethod.setDeleted(resultSet.getBoolean("IsDeleted"));
            return paymentMethod;
        } catch (SQLException e) {
            return null;
        }
    }
}

