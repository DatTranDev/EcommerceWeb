package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.IUserReviewDAO;
import com.EcommerceWeb.dao.impl.UserReviewDAO;
import com.EcommerceWeb.model.UserReview;
import com.EcommerceWeb.service.IUserReviewService;

import javax.inject.Inject;
import java.util.List;

public class UserReviewService implements IUserReviewService {
    @Inject
    private IUserReviewDAO userReviewDAO;

    public int count() {
        return userReviewDAO.count();
    }

    public int add(UserReview userReview) {
        return userReviewDAO.add(userReview);
    }

    public void update(UserReview userReview) {
        userReviewDAO.update(userReview);
    }

    public void delete(int id) {
        userReviewDAO.delete(id);
    }
    public List<UserReview> getGoodReview() {
        return userReviewDAO.getGoodReview();
    }
    public List<UserReview> getReviewByProductID(int productID) {
        return userReviewDAO.getReviewByProductID(productID);
    }
}
