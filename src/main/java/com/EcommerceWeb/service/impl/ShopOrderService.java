package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.*;

import com.EcommerceWeb.model.*;
import com.EcommerceWeb.service.IShopOrderService;
import com.EcommerceWeb.service.IShoppingCartItemService;

import javax.inject.Inject;
import java.sql.Timestamp;
import java.util.ArrayList;
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
    @Inject
    private IShoppingCartItemDAO shoppingCartItemDAO;
    @Inject
    private IProductItemDAO productItemDAO;
    @Inject
    private IOrderLineDAO orderLineDAO;
    @Inject
    private IShoppingCartItemService shoppingCartItemService;
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

    @Override
    public int insertBill(ShopOrderModel shopOrderModel,int[] listShoppingCartItemID) {
        if(listShoppingCartItemID==null)return -1;

        shopOrderModel.setOrderTotal(0);
        shopOrderModel.setOrderDate(new Timestamp(System.currentTimeMillis()));
        int idBillInsert=shopOrderDAO.insert(shopOrderModel);
        if(idBillInsert==-1)return -1;


        double orderTotal = 0;

        for(int shoppingCartID:listShoppingCartItemID){
            ShoppingCartItemModel shoppingCartItemModel= shoppingCartItemDAO.findOne(shoppingCartID);
            if(shoppingCartItemModel==null)return -1;
            ProductItem productItem = productItemDAO.findOne(shoppingCartItemModel.getProductItemID());
            if(productItem==null)return -1;
            shoppingCartItemModel.setProductItem(productItem);

            OrderLineModel orderLineModel = convertShoppingCartItemModelToOrderLineModel(shoppingCartItemModel);
            orderLineModel.setOrderID(idBillInsert);

            if(orderLineDAO.insert(orderLineModel)==-1)return -1;
            orderTotal+=orderLineModel.getPrice();

        }
        shopOrderModel.setOrderTotal(orderTotal);

        shopOrderDAO.update(shopOrderModel);

        shoppingCartItemService.updateListItemIsDeleteTrue(listShoppingCartItemID);

        return idBillInsert;
    }



    private static OrderLineModel convertShoppingCartItemModelToOrderLineModel(ShoppingCartItemModel shoppingCartItemModel){
        OrderLineModel orderLineModel = new OrderLineModel();
        orderLineModel.setProductItemID(shoppingCartItemModel.getProductItemID());
        orderLineModel.setQuantity(shoppingCartItemModel.getQuantity());
        orderLineModel.setPrice(shoppingCartItemModel.getProductItem().getPrice());
        return orderLineModel;
    }
}
