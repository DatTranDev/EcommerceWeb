package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IVariationDAO;
import com.EcommerceWeb.mapper.VariationMapper;
import com.EcommerceWeb.mapper.VariationOptionMapper;
import com.EcommerceWeb.model.Variation;
import com.EcommerceWeb.model.VariationOption;

import java.util.List;

public class VariationDAO extends AbstractDAO<Variation> implements IVariationDAO {
    @Override
    public Variation findOne(int variationID) {
        String sql = "select * from Variation where ID = ? and IsDeleted = 0";
        List<Variation> list = query(sql, new VariationMapper(), variationID);
        return list.isEmpty()?null:list.get(0);
    }
}
