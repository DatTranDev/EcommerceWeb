package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.VariationOption;

public interface IVariationOptionDAO extends GenericDAO<VariationOption> {

    VariationOption findOne(int variationOptionID);

}
