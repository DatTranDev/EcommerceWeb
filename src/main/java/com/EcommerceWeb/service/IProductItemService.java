package com.EcommerceWeb.service;

import com.EcommerceWeb.model.ProductItem;

import java.util.List;

public interface IProductItemService {
    List<ProductItem> getAll();
    List<ProductItem> getProductItemByProductID(int id);
    int add(ProductItem productItem);
    ProductItem update(ProductItem productItem);
    ProductItem updateQuantity(int id, int quantity);
    void delete(int id);
    ProductItem findOne(int id);
    int getTotalItem();
    int getTotalQuantityOfProduct(int productID);
}
