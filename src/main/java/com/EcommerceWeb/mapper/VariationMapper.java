package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.Variation;

public class VariationMapper implements RowMapper<Variation> {
    @Override
    public Variation mapRow(ResultSet resultSet) {
        try {
            Variation variation = new Variation();
            variation.setID(resultSet.getInt("ID"));
            variation.setCategoryID(resultSet.getInt("CategoryID"));
            variation.setDisplayName(resultSet.getString("DisplayName"));
            variation.setDeleted(resultSet.getBoolean("IsDeleted"));
            return variation;
        } catch (SQLException e) {
            return null;
        }
    }
}

