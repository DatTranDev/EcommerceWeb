package com.EcommerceWeb.model;

public class UserReview {
    private int ID;
    private int userID;
    private int orderedProductID;
    private int ratingValue;
    private String comment;
    private boolean isDeleted;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getOrderedProductID() {
        return orderedProductID;
    }

    public void setOrderedProductID(int orderedProductID) {
        this.orderedProductID = orderedProductID;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

