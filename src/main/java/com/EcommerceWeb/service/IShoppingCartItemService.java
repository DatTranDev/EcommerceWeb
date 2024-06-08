package com.EcommerceWeb.service;

import com.EcommerceWeb.model.ShoppingCartItemModel;

import java.util.List;

public interface IShoppingCartItemService {
    List<ShoppingCartItemModel> findByCartID(int cardID);
    List<ShoppingCartItemModel> findAllByUserID(int userID);
    ShoppingCartItemModel findOne(int shoppingCartItemId);
    ShoppingCartItemModel insert(ShoppingCartItemModel shoppingCartItemModel);
    ShoppingCartItemModel update(ShoppingCartItemModel shoppingCartItemModel);
    boolean updateIsDeleteTrue(int shoppingCartItemID);
    boolean updateListItemIsDeleteTrue(int[] ids);
    List<ShoppingCartItemModel> findByListShoppingCartItemID(int[]ids);
    ShoppingCartItemModel findOneByProductItemIdFix(int productItemID);
    ShoppingCartItemModel insertFix(int userID,int productItemID,int quantity);
    ShoppingCartItemModel updateFix(ShoppingCartItemModel shoppingCartItemModel);
}
