package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IUserReviewDAO;
import com.EcommerceWeb.mapper.UserReviewMapper;
import com.EcommerceWeb.model.UserReview;

import java.util.List;

public class UserReviewDAO extends AbstractDAO<UserReview> implements IUserReviewDAO {
    @Override
    public int count() {
        String query = "SELECT COUNT(*) FROM UserReview";
        return count(query);

    }

    @Override
    public int add(UserReview userReview) {
        String query = "INSERT INTO UserReview(userID, orderedProductID, ratingValue, comment) VALUES(?, ?, ?, ?)";
        return insert(query, userReview.getUserID(), userReview.getOrderedProductID(), userReview.getRatingValue(), userReview.getComment());
    }

    @Override
    public void update(UserReview userReview) {
        String query = "UPDATE UserReview SET ratingValue = ?, comment = ? WHERE ID = ?";
        update(query, userReview.getRatingValue(), userReview.getComment(), userReview.getID());
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM UserReview WHERE ID = ?";
        update(query, id);
    }
    @Override
    public List<UserReview> getGoodReview() {
        String query = "SELECT UserReview.*, SiteUser.DisplayName FROM UserReview " +
                "INNER JOIN SiteUser ON UserReview.userID = SiteUser.ID " +
                "WHERE UserReview.ratingValue > 3";
        return query(query, new UserReviewMapper());
    }
}
