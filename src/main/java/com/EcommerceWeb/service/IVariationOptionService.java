package com.EcommerceWeb.service;

import com.EcommerceWeb.model.VariationOption;

import java.util.List;

public interface IVariationOptionService {
    VariationOption findOne(int variationOptionID);
    List<VariationOption> findAllSize();
    List<VariationOption> findAllColor();
    int add(VariationOption variationOption);
    void update(VariationOption variationOption);
    boolean delete(int variationOptionID);
}
