package com.EcommerceWeb.model;

public class VariationOption {
    private int ID;
    private int variationID;
    private String value;
    private boolean isDeleted;


    private Variation variation;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getVariationID() {
        return variationID;
    }

    public void setVariationID(int variationID) {
        this.variationID = variationID;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Variation getVariation() {
        return variation;
    }

    public void setVariation(Variation variation) {
        this.variation = variation;
    }
}

