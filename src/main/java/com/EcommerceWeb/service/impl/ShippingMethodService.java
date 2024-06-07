package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.impl.ShippingMethodDAO;
import com.EcommerceWeb.model.ShippingMethod;
import com.EcommerceWeb.service.IShippingMethodService;
import com.EcommerceWeb.utils.MotSoPhuongThucBoTro;

import javax.inject.Inject;
import java.util.List;

public class ShippingMethodService implements IShippingMethodService {
    @Inject
    private ShippingMethodDAO shippingMethodDAO;
    @Override
    public List<ShippingMethod> findAllNotWhereIsDelete() {
        List<ShippingMethod> shippingMethodList= shippingMethodDAO.findAllNotWhereIsDelete();
        if(shippingMethodList == null)return null;
        for(ShippingMethod item:shippingMethodList){
            if(item.isDeleted()){
                item.setDisplayName(item.getDisplayName()+" ("+"Sắp ra mắt)");
            }
            else{
                item.setDisplayName(item.getDisplayName()+" ("+MotSoPhuongThucBoTro.formatMoney(item.getPrice())+")");
            }
        }
        return shippingMethodList;
    }
}
