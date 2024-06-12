package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IShoppingCartDAO;
import com.EcommerceWeb.dao.IShoppingCartItemDAO;
import com.EcommerceWeb.mapper.ShoppingCartItemMapper;
import com.EcommerceWeb.model.ShoppingCartItemModel;
import com.EcommerceWeb.model.ShoppingCartModel;

import java.util.List;

public class ShoppingCartItemDAO  extends AbstractDAO<ShoppingCartItemModel> implements IShoppingCartItemDAO {


    @Override
    public List<ShoppingCartItemModel> findByCartID(int cardID) {

        String sql = "select * from ShoppingCartItem where CartID = ? and IsDeleted = 0";
        return query(sql,new ShoppingCartItemMapper(),cardID);
    }

    @Override
    public ShoppingCartItemModel findOne(int shoppingCartItemId) {
        String sql = "select * from ShoppingCartItem where ID = ? and IsDeleted = 0";
        List<ShoppingCartItemModel> list = query(sql, new ShoppingCartItemMapper(), shoppingCartItemId);
        return list.isEmpty()?null:list.get(0);

    }

    @Override
    public ShoppingCartItemModel findOneByProductItem(int productItem,int cardID) {
        String sql = "select * from ShoppingCartItem where ProductItemID = ? and IsDeleted = 0 and CartID = ?";
        List<ShoppingCartItemModel> list = query(sql, new ShoppingCartItemMapper(), productItem,cardID);
        return list.isEmpty()?null:list.get(0);
    }

    @Override
    public ShoppingCartItemModel findOneWhereIsDeleteTrue(int shoppingCartItemId) {
        String sql = "select * from ShoppingCartItem where ID = ? and IsDeleted = 1";
        List<ShoppingCartItemModel> list = query(sql, new ShoppingCartItemMapper(), shoppingCartItemId);
        return list.isEmpty()?null:list.get(0);

    }


    @Override
    public int insert(ShoppingCartItemModel shoppingCartItemModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO ShoppingCartItem (CartID, ProductItemID, Quantity)");
        sql.append(" VALUES(?, ?, ?)");
        return insert(sql.toString(), shoppingCartItemModel.getCartID(), shoppingCartItemModel.getProductItemID(), shoppingCartItemModel.getQuantity());
    }

    @Override
    public void update(ShoppingCartItemModel shoppingCartItemModel) {
        StringBuilder sql = new StringBuilder("UPDATE ShoppingCartItem SET CartID = ?, ProductItemID = ?, Quantity = ?, IsDeleted = ?");
        sql.append(" WHERE ID = ?");
        update(sql.toString(), shoppingCartItemModel.getCartID(), shoppingCartItemModel.getProductItemID(), shoppingCartItemModel.getQuantity(), shoppingCartItemModel.isDeleted(),shoppingCartItemModel.getID());
    }

    @Override
    public ShoppingCartItemModel findOneByProductItemId(int productItemID) {
        String sql = "select * from ShoppingCartItem where productItemID = ? and IsDeleted = 0";
        List<ShoppingCartItemModel> list = query(sql, new ShoppingCartItemMapper(), productItemID);
        return list.isEmpty()?null:list.get(0);
    }




    //sau nay nho fix lai cho chung
    @Override
    public ShoppingCartItemModel findOneByProductItemIdFix(int productItemID) {
                String sql = "select * from ShoppingCartItem where productItemID = ? and IsDeleted = 0";
        List<ShoppingCartItemModel> list = query(sql, new ShoppingCartItemMapper(), productItemID);

        if(list==null)return null;

        if(list.isEmpty()){
            ShoppingCartItemModel shoppingCartItemModel = new ShoppingCartItemModel();
            shoppingCartItemModel.setID(-1);
            return shoppingCartItemModel;
        }
        else{
            return list.get(0);
        }
    }


}
