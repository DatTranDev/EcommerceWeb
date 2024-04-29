package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.Promotion;

public class PromotionMapper implements RowMapper<Promotion> {
    @Override
    public Promotion mapRow(ResultSet resultSet) {
        try {
            Promotion promotion = new Promotion();
            promotion.setID(resultSet.getInt("ID"));
            promotion.setDisplayName(resultSet.getString("DisplayName"));
            promotion.setDescription(resultSet.getString("Description"));
            promotion.setDiscountRate(resultSet.getFloat("DiscountRate"));
            promotion.setStartDate(resultSet.getDate("StartDate"));
            promotion.setEndDate(resultSet.getDate("EndDate"));
            promotion.setDeleted(resultSet.getBoolean("IsDeleted"));
            return promotion;
        } catch (SQLException e) {
            return null;
        }
    }
}

