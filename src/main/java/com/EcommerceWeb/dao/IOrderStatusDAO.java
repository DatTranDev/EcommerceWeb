package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.OrderStatus;

import java.util.List;

public interface IOrderStatusDAO extends GenericDAO<OrderStatus> {
    OrderStatus findOneById(int id);
    List<OrderStatus> getAllByStatus(String status);
    List<OrderStatus> getAllOrderStatus();
}
