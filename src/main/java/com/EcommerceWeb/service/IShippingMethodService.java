package com.EcommerceWeb.service;

import com.EcommerceWeb.model.ShippingMethod;

import java.util.List;

public interface IShippingMethodService {
    List<ShippingMethod> findAllNotWhereIsDelete();
}
