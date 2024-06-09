package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IVariationOptionDAO;
import com.EcommerceWeb.mapper.ProductConfigMapper;
import com.EcommerceWeb.mapper.ShoppingCartItemMapper;
import com.EcommerceWeb.mapper.VariationOptionMapper;
import com.EcommerceWeb.model.ProductConfig;
import com.EcommerceWeb.model.ShoppingCartItemModel;
import com.EcommerceWeb.model.VariationOption;

import java.util.List;

public class VariationOptionDAO extends AbstractDAO<VariationOption> implements IVariationOptionDAO {
    @Override
    public VariationOption findOne(int variationOptionID) {
        String sql = "select * from VariationOption where ID = ? and IsDeleted = 0";
        List<VariationOption> list = query(sql, new VariationOptionMapper(), variationOptionID);
        return list.isEmpty()?null:list.get(0);
    }

    @Override
    public int add(VariationOption variationOption) {
        String sql = "INSERT INTO VariationOption (VariationID, Value) \r\n"
                + "VALUES (?, ?)";
        return insert(sql, variationOption.getVariationID(), variationOption.getValue());
    }

    @Override
    public void update(VariationOption variationOption) {
        String sql = "UPDATE VariationOption SET Value = ? WHERE ID = ? and IsDeleted=0";
        update(sql,variationOption.getValue(), variationOption.getID());
    }

    @Override
    public List<VariationOption> findAllSize() {
        String sql = "select * from VariationOption where VariationID=? and IsDeleted = 0";
        List<VariationOption> list = query(sql, new VariationOptionMapper(),1);
        return list.isEmpty()?null:list;
    }

    @Override
    public List<VariationOption> findAllColor() {
        String sql = "select * from VariationOption where VariationID=? and IsDeleted = 0";
        List<VariationOption> list = query(sql, new VariationOptionMapper(),2);
        return list.isEmpty()?null:list;
    }

    @Override
    public boolean checkDelete(int variationOptionID) {
        String sql = "select * from productConfig where VariationID = ? and IsDeleted = 0";
        List<ProductConfig> list=query(sql, new ProductConfigMapper(), variationOptionID);
        if(list==null||list.isEmpty())
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(int variationOptionID) {
        if(checkDelete(variationOptionID))
        {
            String sql = "UPDATE VariationOption SET IsDeleted=1 WHERE ID = ? ";
            update(sql,variationOptionID);
            return true;
        }
        return false;
    }
}
