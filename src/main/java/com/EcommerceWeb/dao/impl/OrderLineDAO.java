package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IOrderLineDAO;
import com.EcommerceWeb.mapper.OrderLineMapper;

import com.EcommerceWeb.model.OrderLineModel;

import java.util.List;

public class OrderLineDAO extends AbstractDAO<OrderLineModel> implements IOrderLineDAO {
    @Override
    public List<OrderLineModel> findByOrderID(int OrderID) {
        String sql = "select * from OrderLine where OrderID = ? and IsDeleted = 0";
        return query(sql,new OrderLineMapper(),OrderID);
    }

    @Override
    public int insert(OrderLineModel orderLineModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO OrderLine (ProductItemID, OrderID, Quantity, Price)");
        sql.append(" VALUES(?, ?, ?, ?)");
        return insert(sql.toString(),
                orderLineModel.getProductItemID(),
                orderLineModel.getOrderID(),
                orderLineModel.getQuantity(),
                orderLineModel.getPrice());
    }

}
