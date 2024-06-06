package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.impl.ShippingMethodDAO;
import com.EcommerceWeb.model.ShippingMethod;
import com.EcommerceWeb.service.IShippingMethodService;

import javax.inject.Inject;
import java.util.List;

public class ShippingMethodService implements IShippingMethodService {
    @Inject
    private ShippingMethodDAO shippingMethodDAO;
    @Override
    public List<ShippingMethod> findAllNotWhereIsDelete() {
        return shippingMethodDAO.findAllNotWhereIsDelete();
    }
}
