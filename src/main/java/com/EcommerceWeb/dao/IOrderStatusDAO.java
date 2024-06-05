package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.OrderStatus;

public interface IOrderStatusDAO extends GenericDAO<OrderStatus> {
    OrderStatus findOneById(int id);
}
