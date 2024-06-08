package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.Variation;

public interface IVariationDAO extends GenericDAO<Variation> {
    Variation findOne(int variationID);
}
