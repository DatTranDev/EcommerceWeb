package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IOrderStatusDAO;
import com.EcommerceWeb.mapper.AddressMapper;
import com.EcommerceWeb.mapper.OrderStatusMapper;
import com.EcommerceWeb.model.Address;
import com.EcommerceWeb.model.OrderStatus;

import java.util.List;

public class OrderStatusDAO extends AbstractDAO<OrderStatus> implements IOrderStatusDAO {
    @Override
    public OrderStatus findOneById(int id) {
        String sql = "select * from OrderStatus where ID = ?";
        List<OrderStatus> list = query(sql, new OrderStatusMapper(), id);
        return list.isEmpty()?null:list.get(0);
    }
    public List<OrderStatus> getAllByStatus(String status) {
        String sql = "select * from OrderStatus where status = ?";
        List<OrderStatus> list = query(sql, new OrderStatusMapper(),status);
        return list.isEmpty()?null:list;
    }
}
