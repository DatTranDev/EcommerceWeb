package com.EcommerceWeb.model;

public class ProductConfig {
    private int productItemID;
    private int variationID;
    private boolean isDeleted;

    public int getProductItemID() {
        return productItemID;
    }

    public void setProductItemID(int productItemID) {
        this.productItemID = productItemID;
    }

    public int getVariationID() {
        return variationID;
    }

    public void setVariationID(int variationID) {
        this.variationID = variationID;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}

