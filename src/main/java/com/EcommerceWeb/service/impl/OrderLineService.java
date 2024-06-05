package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.IOrderLineDAO;
import com.EcommerceWeb.dao.IProductItemDAO;
import com.EcommerceWeb.dao.impl.ProductItemDAO;
import com.EcommerceWeb.model.OrderLineModel;
import com.EcommerceWeb.model.ProductItem;
import com.EcommerceWeb.service.IOrderLineService;

import javax.inject.Inject;
import java.util.List;

public class OrderLineService implements IOrderLineService {


    @Inject
    private IOrderLineDAO orderLineDAO;
    @Inject
    private IProductItemDAO productItemDAO;
    @Override
    public List<OrderLineModel> findByOrderID(int OrderID) {
        List<OrderLineModel> list=orderLineDAO.findByOrderID(OrderID);

        if(list==null)return null;

        for(OrderLineModel item:list){
            ProductItem productItem= productItemDAO.findOne(item.getProductItemID());
            item.setProductItem(productItem);
        }

        return list;
    }
}
