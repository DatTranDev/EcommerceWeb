package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IVariationOptionDAO;
import com.EcommerceWeb.mapper.ShoppingCartItemMapper;
import com.EcommerceWeb.mapper.VariationOptionMapper;
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
}
