package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.impl.ShoppingCartDAO;
import com.EcommerceWeb.model.ShoppingCartModel;
import com.EcommerceWeb.service.IShoppingCartService;

import javax.inject.Inject;

public class ShoppingCartService implements IShoppingCartService {

    @Inject
    private ShoppingCartDAO shoppingCartDAO;

    @Override
    public ShoppingCartModel findOneByUserID(int userID) {
        return shoppingCartDAO.findOneByUserID(userID);
    }
}
