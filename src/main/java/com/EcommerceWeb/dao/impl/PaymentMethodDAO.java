package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IPaymentMethodDAO;
import com.EcommerceWeb.mapper.OrderStatusMapper;
import com.EcommerceWeb.mapper.PaymentMethodMapper;
import com.EcommerceWeb.model.OrderStatus;
import com.EcommerceWeb.model.PaymentMethod;

import java.util.List;

public class PaymentMethodDAO extends AbstractDAO<PaymentMethod> implements IPaymentMethodDAO {
    @Override
    public PaymentMethod findOneById(int id) {
        String sql = "select * from PaymentMethod where ID = ?";
        List<PaymentMethod> list = query(sql, new PaymentMethodMapper(), id);
        return list.isEmpty()?null:list.get(0);
    }
}
