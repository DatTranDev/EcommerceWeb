package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IUserReviewDAO;
import com.EcommerceWeb.mapper.OrderLineMapper;
import com.EcommerceWeb.mapper.UserReviewMapper;
import com.EcommerceWeb.model.OrderLineModel;
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
        String query = "SELECT UserReview.*, SiteUser.DisplayName as UserName FROM UserReview " +
                "INNER JOIN SiteUser ON UserReview.userID = SiteUser.ID " +
                "WHERE UserReview.ratingValue > 3";
        return query(query, new UserReviewMapper());
    }
    @Override
    public List<UserReview> getReviewByProductID(int productID) {
        String query = "SELECT ur.*, su.DisplayName AS UserName " +
                "FROM UserReview ur " +
                "JOIN OrderLine ol ON ur.OrderedProductID = ol.ID " +
                "JOIN ProductItem pi ON ol.ProductItemID = pi.ID " +
                "JOIN Product p ON pi.ProductID = p.ID " +
                "JOIN SiteUser su ON ur.UserID = su.ID " +
                "WHERE p.ID = ? AND ur.IsDeleted = 0";
        return query(query, new UserReviewMapper(), productID);
    }
    @Override
    public boolean checkUserReview(int userID, int orderedProductID) {
        String query = "SELECT ur.*, su.DisplayName AS UserName " +
                "FROM UserReview ur " +
                "JOIN OrderLine ol ON ur.OrderedProductID = ol.ID " +
                "JOIN ProductItem pi ON ol.ProductItemID = pi.ID " +
                "JOIN SiteUser su ON ur.UserID = su.ID " +
                "WHERE ur.UserID = ? AND pi.ProductID = ? AND ur.IsDeleted = 0";
        List<UserReview> userReviews = query(query, new UserReviewMapper(), userID, orderedProductID);
        String query2 = "SELECT COUNT(*) " +
                "FROM OrderLine ol " +
                "JOIN ShopOrder so ON ol.OrderID = so.ID " +
                "JOIN ProductItem pi ON ol.ProductItemID = pi.ID " +
                "WHERE so.UserID = ? AND pi.ProductID = ?";
        int count = count(query2, userID, orderedProductID);
        return userReviews.isEmpty() && count > 0;
    }
    @Override
    public int getOrderLineID(int userID, int productID) {
        String query = "SELECT ol.* " +
                "FROM OrderLine ol " +
                "JOIN ShopOrder so ON ol.OrderID = so.ID " +
                "JOIN ProductItem pi ON ol.ProductItemID = pi.ID " +
                "WHERE so.UserID = ? AND pi.ProductID = ?";
        List<OrderLineModel> orderLineIDs = query(query, new OrderLineMapper(), userID, productID);
        if(orderLineIDs.isEmpty())
            return -1;
        else
            return orderLineIDs.get(0).getID();
    }

}
