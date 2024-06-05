package com.EcommerceWeb.service;

import com.EcommerceWeb.model.ShoppingCartItemModel;

import java.util.List;

public interface IShoppingCartItemService {
    List<ShoppingCartItemModel> findByCartID(int cardID);
    List<ShoppingCartItemModel> findAllByUserID(int userID);
    ShoppingCartItemModel findOne(int shoppingCartItemId);
    ShoppingCartItemModel insert(ShoppingCartItemModel shoppingCartItemModel);
    ShoppingCartItemModel update(ShoppingCartItemModel shoppingCartItemModel);

}
