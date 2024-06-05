package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.impl.ProductItemDAO;
import com.EcommerceWeb.dao.impl.ShoppingCartDAO;
import com.EcommerceWeb.dao.impl.ShoppingCartItemDAO;
import com.EcommerceWeb.model.ProductItem;
import com.EcommerceWeb.model.ShoppingCartItemModel;
import com.EcommerceWeb.model.ShoppingCartModel;
import com.EcommerceWeb.service.IShoppingCartItemService;

import javax.inject.Inject;
import java.util.List;

public class ShoppingCartItemService implements IShoppingCartItemService {

    @Inject
    private ShoppingCartItemDAO shoppingCartItemDAO;
    @Inject
    private ProductItemDAO productItemDAO;
    @Inject
    private ShoppingCartDAO shoppingCartDAO;



    //tim toan bo item theo id cua gio hang
    @Override
    public List<ShoppingCartItemModel> findByCartID(int cardID) {
        List<ShoppingCartItemModel> list = shoppingCartItemDAO.findByCartID(cardID);

        if(list==null){
            return null;
        }

        for(ShoppingCartItemModel item : list){
            ProductItem productItem= productItemDAO.findOne(item.getProductItemID());
            item.setProductItem(productItem);
        }

        return list;
    }



    //tim toan bo item co trong gio hang cua user
    @Override
    public List<ShoppingCartItemModel> findAllByUserID(int userID) {

        ShoppingCartModel shoppingCartModel= shoppingCartDAO.findOneByUserID(userID);
        if(shoppingCartModel==null)return null;

        return findByCartID(shoppingCartModel.getID());
    }

    @Override
    public ShoppingCartItemModel findOne(int shoppingCartItemId) {
        return shoppingCartItemDAO.findOne(shoppingCartItemId);
    }

    @Override
    public ShoppingCartItemModel insert(ShoppingCartItemModel shoppingCartItemModel) {

        int iDInsert= shoppingCartItemDAO.insert(shoppingCartItemModel);

        if(iDInsert==-1)return null;

        return findOne(iDInsert);

    }

    @Override
    public ShoppingCartItemModel update(ShoppingCartItemModel shoppingCartItemModel) {

        shoppingCartItemDAO.update(shoppingCartItemModel);

        return findOne(shoppingCartItemModel.getID());

    }


}
