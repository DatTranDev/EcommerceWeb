package com.EcommerceWeb.service;

import com.EcommerceWeb.model.OrderLineModel;

import java.util.List;

public interface IOrderLineService {
    List<OrderLineModel> findByOrderID(int OrderID);
}
