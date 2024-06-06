package com.EcommerceWeb.service;

import com.EcommerceWeb.model.PaymentMethod;

import java.util.List;

public interface IPaymentMethodService {
    List<PaymentMethod> findAllNotWhereIsDelete();
}
