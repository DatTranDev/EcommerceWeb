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
        List<PaymentMethod> paymentMethodList = paymentMethodDAO.findAllNotWhereIsDelete();
        if(paymentMethodList==null)return null;

        for(PaymentMethod item:paymentMethodList){
            if(item.isDeleted()){
                item.setDisplayName(item.getDisplayName()+" ("+"Sắp ra mắt)");
            }
        }
        return paymentMethodList;
    }
}
