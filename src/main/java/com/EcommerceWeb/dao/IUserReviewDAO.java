package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.UserReview;

import java.util.List;

public interface IUserReviewDAO {
    int count();
    int add(UserReview userReview);
    void update(UserReview userReview);
    void delete(int id);
    List<UserReview> getGoodReview();
}
