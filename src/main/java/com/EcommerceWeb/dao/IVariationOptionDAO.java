package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.VariationOption;

import java.util.List;

public interface IVariationOptionDAO extends GenericDAO<VariationOption> {

    VariationOption findOne(int variationOptionID);
    List<VariationOption> findAllSize();
    List<VariationOption> findAllColor();
    int add(VariationOption variationOption);
    void update(VariationOption variationOption);
    boolean checkDelete(int variationOptionID);
    boolean delete(int variationOptionID);
}
