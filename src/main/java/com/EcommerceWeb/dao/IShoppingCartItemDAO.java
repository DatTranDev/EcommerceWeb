package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.ShoppingCartItemModel;

import java.util.List;

public interface IShoppingCartItemDAO extends GenericDAO<ShoppingCartItemModel> {
    List<ShoppingCartItemModel> findByCartID(int cardID);
    ShoppingCartItemModel findOne(int shoppingCartItemId);
    ShoppingCartItemModel findOneByProductItem(int productItem,int cardID);
    int insert(ShoppingCartItemModel shoppingCartItemModel);
    void update(ShoppingCartItemModel shoppingCartItemModel);
    ShoppingCartItemModel findOneByProductItemId(int productItemID);
    ShoppingCartItemModel findOneWhereIsDeleteTrue(int shoppingCartItemId);
    ShoppingCartItemModel findOneByProductItemIdFix(int productItemID);
}
