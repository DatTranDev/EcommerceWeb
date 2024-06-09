package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.IVariationDAO;
import com.EcommerceWeb.dao.IVariationOptionDAO;
import com.EcommerceWeb.dao.impl.VariationOptionDAO;
import com.EcommerceWeb.model.VariationOption;
import com.EcommerceWeb.service.IVariationOptionService;

import javax.inject.Inject;
import java.util.List;

public class VariationOptionService implements IVariationOptionService {


    @Inject
    private IVariationOptionDAO variationOptionDAO;
    @Inject
    private IVariationDAO variationDAO;

    @Override
    public VariationOption findOne(int variationOptionID) {

        VariationOption variationOption= variationOptionDAO.findOne(variationOptionID);
        if(variationOption!=null){
            variationOption.setVariation(variationDAO.findOne(variationOption.getVariationID()));
        }
        return  variationOption;

    }

    @Override
    public int add(VariationOption variationOption) {
        return variationOptionDAO.add(variationOption);
    }

    @Override
    public void update(VariationOption variationOption) {
        variationOptionDAO.update(variationOption);
    }

    @Override
    public boolean delete(int variationOptionID) {
        return variationOptionDAO.delete(variationOptionID);
    }

    @Override
    public List<VariationOption> findAllSize() {
        return variationOptionDAO.findAllSize();
    }
    @Override
    public List<VariationOption> findAllColor() {
        return variationOptionDAO.findAllColor();
    }
}
