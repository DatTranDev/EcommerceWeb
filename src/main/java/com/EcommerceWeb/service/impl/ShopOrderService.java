package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.*;

import com.EcommerceWeb.model.*;
import com.EcommerceWeb.service.IShopOrderService;

import javax.inject.Inject;
import java.util.List;

public class ShopOrderService implements IShopOrderService {
    @Inject
    private IShopOrderDAO shopOrderDAO;
    @Inject
    private IPaymentMethodDAO paymentMethodDAO;
    @Inject
    private IAddressDAO addressDAO;
    @Inject
    private IShippingMethodDAO shippingMethodDAO;
    @Inject
    private IOrderStatusDAO orderStatusDAO;
    @Override
    public List<ShopOrderModel> findByUserIdAndOrderStatusID(int userID, int orderStatusID) {
        List<ShopOrderModel> list=shopOrderDAO.findByUserIdAndOrderStatusID(userID,orderStatusID);
        if(list==null)return null;

        for(ShopOrderModel item:list){

            PaymentMethod paymentMethod=paymentMethodDAO.findOneById(item.getPaymentMethodID());
            Address address = addressDAO.findOne(item.getShippingAddressID());
            ShippingMethod shippingMethod = shippingMethodDAO.findOneById(item.getShippingMethodID());
            OrderStatus orderStatus=orderStatusDAO.findOneById(item.getOrderStatusID());

        }
        return  list;
    }

    @Override
    public ShopOrderModel insert(ShopOrderModel shopOrderModel) {
        int idInsert= shopOrderDAO.insert(shopOrderModel);
        if(idInsert==-1)return null;

        return shopOrderDAO.findOne(idInsert);
    }
}
