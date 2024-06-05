package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.ShippingMethod;

public interface IShippingMethodDAO extends GenericDAO<ShippingMethod> {
    ShippingMethod findOneById(int id);
}
