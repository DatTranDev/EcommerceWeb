package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.PaymentMethod;

public interface IPaymentMethodDAO extends GenericDAO<PaymentMethod> {

    PaymentMethod findOneById(int id);
}
