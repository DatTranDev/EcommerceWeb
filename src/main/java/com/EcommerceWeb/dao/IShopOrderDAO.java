package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.ShopOrderModel;

import java.util.List;

public interface IShopOrderDAO extends GenericDAO<ShopOrderModel>{

    List<ShopOrderModel>findByUserIdAndOrderStatusID(int userID,int orderStatusID);

    int insert(ShopOrderModel shopOrderModel);

    ShopOrderModel findOne(int id);
    void update(ShopOrderModel shopOrderModel);

    List<ShopOrderModel> findAllByUserID(int userID);
    List<ShopOrderModel> getAll();
}
