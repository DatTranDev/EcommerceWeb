package com.EcommerceWeb.service;

import com.EcommerceWeb.model.ShoppingCartModel;

public interface IShoppingCartService {
    ShoppingCartModel findOneByUserID(int userID);
}
