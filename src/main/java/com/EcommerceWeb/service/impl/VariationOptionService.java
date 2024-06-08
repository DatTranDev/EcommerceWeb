package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.IVariationDAO;
import com.EcommerceWeb.dao.IVariationOptionDAO;
import com.EcommerceWeb.dao.impl.VariationOptionDAO;
import com.EcommerceWeb.model.VariationOption;
import com.EcommerceWeb.service.IVariationOptionService;

import javax.inject.Inject;

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
}
