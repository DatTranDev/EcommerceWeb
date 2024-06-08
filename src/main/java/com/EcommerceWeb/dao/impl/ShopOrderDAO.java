package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IShopOrderDAO;
import com.EcommerceWeb.mapper.ShopOrderMapper;
import com.EcommerceWeb.mapper.ShoppingCartItemMapper;
import com.EcommerceWeb.model.ShopOrderModel;
import com.EcommerceWeb.model.ShoppingCartItemModel;

import java.util.List;

public class ShopOrderDAO extends AbstractDAO<ShopOrderModel> implements IShopOrderDAO {

    @Override
    public List<ShopOrderModel> findByUserIdAndOrderStatusID(int userID, int orderStatusID) {
        String sql = "select * from ShoppingCartItem where ShopOrder = ? and OrderStatusID = ? and IsDeleted = 0";
        return query(sql,new ShopOrderMapper(),userID,orderStatusID);
    }

    @Override
    public int insert(ShopOrderModel shopOrderModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO ShopOrder (UserID, PaymentMethodID, ShippingAddressID, ShippingMethodID, Description)");
        sql.append(" VALUES(?, ?, ?, ?, ?)");
        return insert(sql.toString(),
                shopOrderModel.getUserID(),
                shopOrderModel.getPaymentMethodID(),
                shopOrderModel.getShippingAddressID(),
                shopOrderModel.getShippingMethodID(),
                shopOrderModel.getDescription());
    }

    @Override
    public ShopOrderModel findOne(int id) {
        String sql = "select * from ShopOrder where ID = ? and IsDeleted = 0";
        List<ShopOrderModel> list = query(sql, new ShopOrderMapper(), id);
        return list.isEmpty()?null:list.get(0);
    }

    @Override
    public void update(ShopOrderModel shopOrderModel) {
        StringBuilder sql = new StringBuilder("UPDATE ShopOrder SET UserID = ?, PaymentMethodID = ?, ShippingAddressID = ?, ShippingMethodID = ?, OrderTotal = ?, OrderStatusID = ?, Description = ?, IsDeleted = ?");
        sql.append(" WHERE ID = ?");
        update(sql.toString(),
                shopOrderModel.getUserID(),
                shopOrderModel.getPaymentMethodID(),
                shopOrderModel.getShippingAddressID(),
                shopOrderModel.getShippingMethodID(),
                shopOrderModel.getOrderTotal(),
                shopOrderModel.getOrderStatusID(),
                shopOrderModel.getDescription(),
                shopOrderModel.isDeleted(),
                shopOrderModel.getID());
    }


}
