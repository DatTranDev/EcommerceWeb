package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.OrderLineModel;


import java.util.List;


public interface IOrderLineDAO extends GenericDAO<OrderLineModel>{
    List<OrderLineModel> findByOrderID(int OrderID);
    int insert(OrderLineModel orderLineModel);
}
