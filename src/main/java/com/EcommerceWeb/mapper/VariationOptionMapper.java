package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.VariationOption;

public class VariationOptionMapper implements RowMapper<VariationOption> {
    @Override
    public VariationOption mapRow(ResultSet resultSet) {
        try {
            VariationOption option = new VariationOption();
            option.setID(resultSet.getInt("ID"));
            option.setVariationID(resultSet.getInt("VariationID"));
            option.setValue(resultSet.getString("Value"));
            option.setDeleted(resultSet.getBoolean("IsDeleted"));
            return option;
        } catch (SQLException e) {
            return null;
        }
    }
}

