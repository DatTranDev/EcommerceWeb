package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.IProductItemDAO;
import com.EcommerceWeb.model.ProductItem;
import com.EcommerceWeb.service.IProductItemService;

import java.util.List;

public class ProductItemService implements IProductItemService {
    private IProductItemDAO productItemDAO;
    @Override
    public List<ProductItem> getAll() {
        return productItemDAO.getAll()  ;
    }

    @Override
    public List<ProductItem> getProductItemByProductID(int id) {
        return productItemDAO.getProductItemByProductID(id);
    }

    @Override
    public int add(ProductItem productItem) {
        return productItemDAO.add(productItem);
    }

    @Override
    public ProductItem update(ProductItem productItem) {
        int id = productItem.getID();
        productItemDAO.update(productItem);
        return productItemDAO.findOne(id);
    }

    @Override
    public ProductItem updateQuantity(int id, int quantity) {
        productItemDAO.updateQuantity(id,quantity);
        return productItemDAO.findOne(id);
    }

    @Override
    public void delete(int id) {
        productItemDAO.delete(id);
    }

    @Override
    public ProductItem findOne(int id) {
        return productItemDAO.findOne(id);
    }

    @Override
    public int getTotalItem() {
        return productItemDAO.getTotalItem();
    }

    @Override
    public int getTotalQuantityOfProduct(int productID) {
        return productItemDAO.getTotalQuantityOfProduct(productID);
    }
}
