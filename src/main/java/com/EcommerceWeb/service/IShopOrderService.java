package com.EcommerceWeb.service;

import com.EcommerceWeb.model.ShopOrderModel;

import java.util.List;

public interface IShopOrderService {
    List<ShopOrderModel> findByUserIdAndOrderStatusID(int userID, int orderStatusID);
    ShopOrderModel insert(ShopOrderModel shopOrderModel);

    int insertBill(ShopOrderModel shopOrderModel,int[] listShoppingCartItemID);

    List<ShopOrderModel> findAllByUserID(int userID);
    List<ShopOrderModel> getAll();
    List<ShopOrderModel> findAllByOrderStatus();
//    int calculateRevenueByMonth(int month);
//    int calculateRevenueByYear(int year);

}
