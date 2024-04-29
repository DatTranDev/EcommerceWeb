package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.PromotionCategory;

public class PromotionCategoryMapper implements RowMapper<PromotionCategory> {
    @Override
    public PromotionCategory mapRow(ResultSet resultSet) {
        try {
            PromotionCategory promotionCategory = new PromotionCategory();
            promotionCategory.setCategoryID(resultSet.getInt("CategoryID"));
            promotionCategory.setPromotionID(resultSet.getInt("PromotionID"));
            promotionCategory.setDeleted(resultSet.getBoolean("IsDeleted"));
            return promotionCategory;
        } catch (SQLException e) {
            return null;
        }
    }
}

