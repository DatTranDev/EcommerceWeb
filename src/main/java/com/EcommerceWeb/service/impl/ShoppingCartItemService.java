package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.IProductItemDAO;
import com.EcommerceWeb.dao.IShoppingCartDAO;
import com.EcommerceWeb.dao.IShoppingCartItemDAO;
import com.EcommerceWeb.model.ProductItem;
import com.EcommerceWeb.model.ShoppingCartItemModel;
import com.EcommerceWeb.model.ShoppingCartModel;
import com.EcommerceWeb.service.IProductItemService;
import com.EcommerceWeb.service.IShoppingCartItemService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartItemService implements IShoppingCartItemService {

    @Inject
    private IShoppingCartItemDAO shoppingCartItemDAO;
    @Inject
    private IProductItemDAO productItemDAO;
    @Inject
    private IShoppingCartDAO shoppingCartDAO;
    @Inject
    private IProductItemService productItemService;
    //tim toan bo item theo id cua gio hang
    @Override
    public List<ShoppingCartItemModel> findByCartID(int cardID) {
        List<ShoppingCartItemModel> list = shoppingCartItemDAO.findByCartID(cardID);

        if(list==null){
            return null;
        }
        List<ShoppingCartItemModel> listDeleted = shoppingCartItemDAO.findByCartID(cardID);

        for(ShoppingCartItemModel item : list){
            ProductItem productItem= productItemService.findOne(item.getProductItemID());

            item.setProductItem(productItem);

            if(productItem==null){
                listDeleted.add(item);
            }
            //item.getProductItem().setQuantityInStock(item.getQuantity());
        }


        for(ShoppingCartItemModel item : listDeleted){
            list.remove(item);
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
    public ShoppingCartItemModel findOneByProductItem(int productItemId, int cardID) {
        ShoppingCartItemModel shoppingCartModel = shoppingCartItemDAO.findOneByProductItem(productItemId,cardID);
        if(shoppingCartModel==null)return null;
        return shoppingCartModel;
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

    @Override
    public boolean updateIsDeleteTrue(int shoppingCartItemID) {
        ShoppingCartItemModel shoppingCartItemModel = shoppingCartItemDAO.findOne(shoppingCartItemID);
        if(shoppingCartItemModel==null)return false;

        shoppingCartItemModel.setDeleted(true);
        shoppingCartItemDAO.update(shoppingCartItemModel);

        ShoppingCartItemModel result=shoppingCartItemDAO.findOneWhereIsDeleteTrue(shoppingCartItemModel.getID());
        if(result==null){
            return false;
        }
        return true;
    }

    @Override
    public boolean updateListItemIsDeleteTrue(int[] ids) {
        for (int id : ids) {
            boolean check=updateIsDeleteTrue(id);
            if(!check)return false;
        }
        return true;
    }

    @Override
    public List<ShoppingCartItemModel> findByListShoppingCartItemID(int[] ids) {
        List<ShoppingCartItemModel> list = new ArrayList<>();
        for(int id:ids){
            ShoppingCartItemModel shoppingCartItemModel = shoppingCartItemDAO.findOne(id);
            if(shoppingCartItemModel==null)return null;

            ProductItem productItem= productItemService.findOne(shoppingCartItemModel.getProductItemID());
            if(productItem==null)return null;
            shoppingCartItemModel.setProductItem(productItem);

            list.add(shoppingCartItemModel);
        }
        return list;
    }

    @Override
    public ShoppingCartItemModel findOneByProductItemIdFix(int productItemID) {
        return shoppingCartItemDAO.findOneByProductItemIdFix(productItemID);
    }




    @Override
    public ShoppingCartItemModel insertFix(int userID, int productItemID, int quantity) {

        ShoppingCartModel shoppingCartModel = shoppingCartDAO.findOneByUserID(userID);
        if(shoppingCartModel==null)return null;

        ShoppingCartItemModel shoppingCartItemModel = new ShoppingCartItemModel();
        shoppingCartItemModel.setCartID(shoppingCartModel.getID());
        shoppingCartItemModel.setProductItemID(productItemID);
        shoppingCartItemModel.setQuantity(quantity);
        shoppingCartItemModel.setDeleted(false);

        int iDInsert= shoppingCartItemDAO.insert(shoppingCartItemModel);

        if(iDInsert==-1)return null;

        return findOne(iDInsert);
    }

    @Override
    public ShoppingCartItemModel updateFix(ShoppingCartItemModel shoppingCartItemModel) {
       return update(shoppingCartItemModel);
    }


}
