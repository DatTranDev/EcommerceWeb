package com.EcommerceWeb.dao;

import com.EcommerceWeb.model.ShoppingCartModel;

public interface IShoppingCartDAO extends GenericDAO<ShoppingCartModel>{
    ShoppingCartModel findOneByUserID(int userID);

}
