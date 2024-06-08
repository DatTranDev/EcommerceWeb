package com.EcommerceWeb.service;

import com.EcommerceWeb.model.OrderStatus;

import java.util.List;

public interface IOrderStatusService {
    List<OrderStatus> getAllOrderByStatus(String status);
    List<OrderStatus> getAllOrder();
}
