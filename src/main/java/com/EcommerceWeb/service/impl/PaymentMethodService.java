package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.impl.PaymentMethodDAO;
import com.EcommerceWeb.model.PaymentMethod;
import com.EcommerceWeb.service.IPaymentMethodService;

import javax.inject.Inject;
import java.util.List;

public class PaymentMethodService implements IPaymentMethodService {
    @Inject
    private PaymentMethodDAO paymentMethodDAO;

    @Override
    public List<PaymentMethod> findAllNotWhereIsDelete() {
        return paymentMethodDAO.findAllNotWhereIsDelete();
    }
}
