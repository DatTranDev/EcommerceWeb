package com.EcommerceWeb.service;

import com.EcommerceWeb.model.ProductItem;

import java.util.List;

public interface IProductItemService {
    List<ProductItem> getAll();
    List<ProductItem> getAllisDelete();
    List<ProductItem> getProductItemByProductID(int id);
    int add(ProductItem productItem);
    ProductItem update(ProductItem productItem);
    ProductItem updateQuantity(int id, int quantity);
    void delete(int id);
    ProductItem findOne(int id);
    ProductItem findOnee(int id);
    int getTotalItem();
    int getTotalQuantityOfProduct(int productID);


    //phuc vu phan chi tiet san pham de chon size mau
    List<ProductItem> getProductItemByProductIDForProductDetail(int productID);
}
