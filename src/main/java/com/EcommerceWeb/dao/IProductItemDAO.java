package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductItem;

import java.util.List;

public interface IProductItemDAO extends  GenericDAO<ProductItem>{
    List<ProductItem> getAll();
    List<ProductItem> getAllisDelete();
    List<ProductItem> getProductItemByProductID(int id);
    int add(ProductItem productItem);
    void update(ProductItem productItem);
    void updateQuantity(int id, int quantity);
    void delete(int id);
    ProductItem findOne(int id);
    ProductItem findOnee(int id);
    ProductItem findOneNotWhereIsDeleted(int id);
    int getTotalItem();
    int getTotalQuantityOfProduct(int productID);
}
