package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.ShippingMethod;

import java.util.List;

public interface IShippingMethodDAO extends GenericDAO<ShippingMethod> {
    ShippingMethod findOneById(int id);
    List<ShippingMethod> findAllNotWhereIsDelete();
}
