package com.EcommerceWeb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.EcommerceWeb.model.UserReview;

public class UserReviewMapper implements RowMapper<UserReview> {
    @Override
    public UserReview mapRow(ResultSet resultSet) {
        try {
            UserReview userReview = new UserReview();
            userReview.setID(resultSet.getInt("ID"));
            userReview.setUserID(resultSet.getInt("UserID"));
            userReview.setOrderedProductID(resultSet.getInt("OrderedProductID"));
            userReview.setRatingValue(resultSet.getInt("RatingValue"));
            userReview.setComment(resultSet.getString("Comment"));
            userReview.setDeleted(resultSet.getBoolean("IsDeleted"));
            userReview.setUsername(resultSet.getString("UserName"));
            return userReview;
        } catch (SQLException e) {
            return null;
        }
    }
}
