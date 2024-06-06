package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.PaymentMethod;

import java.util.List;

public interface IPaymentMethodDAO extends GenericDAO<PaymentMethod> {

    PaymentMethod findOneById(int id);


    List<PaymentMethod> findAllNotWhereIsDelete();
}
