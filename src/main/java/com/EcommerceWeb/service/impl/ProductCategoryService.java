package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.IProductCategoryDAO;
import com.EcommerceWeb.dao.IProductDAO;
import com.EcommerceWeb.service.IProductCategoryService;
import com.EcommerceWeb.model.ProductCategory;

import javax.inject.Inject;
import java.util.List;
public class ProductCategoryService implements IProductCategoryService {

    @Inject
    private IProductCategoryDAO productCategoryDAO;
    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> rs = productCategoryDAO.getAll();
        return rs;
    }

    @Override
    public ProductCategory add(ProductCategory productCategory) {
        int productCategoryId = productCategoryDAO.add(productCategory);
        return productCategoryDAO.findOne(productCategoryId);
    }

    @Override
    public ProductCategory update(ProductCategory productCategory) {
        int id = productCategory.getID();
        productCategoryDAO.update(productCategory);
        return productCategoryDAO.findOne(id);
    }

    @Override
    public void delete(int id) {
        productCategoryDAO.delete(id);
    }

    @Override
    public ProductCategory findOne(int id) {
        return productCategoryDAO.findOne(id);
    }
}
