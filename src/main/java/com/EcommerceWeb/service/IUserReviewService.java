package com.EcommerceWeb.service;

import com.EcommerceWeb.model.UserReview;

import java.util.List;

public interface IUserReviewService {
    int count();
    int add(UserReview userReview);
    void update(UserReview userReview);
    void delete(int id);
    List<UserReview> getGoodReview();
    List<UserReview> getReviewByProductID(int productID);
}
