package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.IOrderStatusDAO;
import com.EcommerceWeb.model.OrderStatus;
import com.EcommerceWeb.service.IOrderStatusService;

import javax.inject.Inject;
import java.util.List;

public class OrderStatusService implements IOrderStatusService {
    @Inject
    private IOrderStatusDAO OrderStatusDAO;
    public List<OrderStatus> getAllOrderByStatus(String status){
        return OrderStatusDAO.getAllByStatus(status);
    }
}
