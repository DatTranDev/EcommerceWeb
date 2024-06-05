package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IShippingMethodDAO;
import com.EcommerceWeb.mapper.PaymentMethodMapper;
import com.EcommerceWeb.mapper.ShippingMethodMapper;
import com.EcommerceWeb.model.PaymentMethod;
import com.EcommerceWeb.model.ShippingMethod;

import java.util.List;

public class ShippingMethodDAO extends AbstractDAO<ShippingMethod> implements IShippingMethodDAO {
    @Override
    public ShippingMethod findOneById(int id) {
        String sql = "select * from ShippingMethod where ID = ?";
        List<ShippingMethod> list = query(sql, new ShippingMethodMapper(), id);
        return list.isEmpty()?null:list.get(0);
    }
}
