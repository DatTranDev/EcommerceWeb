package com.EcommerceWeb.service.impl;

import com.EcommerceWeb.dao.IProductConfigDAO;
import com.EcommerceWeb.dao.IProductDAO;
import com.EcommerceWeb.dao.IProductItemDAO;
import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductConfig;
import com.EcommerceWeb.model.ProductItem;
import com.EcommerceWeb.service.IProductConfigService;
import com.EcommerceWeb.service.IProductItemService;
import com.EcommerceWeb.service.IProductService;

import javax.inject.Inject;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ProductItemService implements IProductItemService {
    @Inject
    private IProductItemDAO productItemDAO;
    @Inject
    private IProductDAO productDAO;
    @Inject
    private IProductConfigDAO productConfigDAO;
    @Inject
    private IProductConfigService productConfigService;


    @Override
    public List<ProductItem> getAll() {
        List<ProductItem> result = productItemDAO.getAll();
        if(result==null) return null;
        for(ProductItem productItem : result) {
            Product product=productDAO.findOne(productItem.getProductID());
            productItem.setProduct(product);
        }
        return result;
    }

    @Override
    public List<ProductItem> getProductItemByProductID(int id) {
        return productItemDAO.getProductItemByProductID(id);
    }

    @Override
    public int add(ProductItem productItem) {
        return productItemDAO.add(productItem);
    }

    @Override
    public ProductItem update(ProductItem productItem) {
        int id = productItem.getID();
        productItemDAO.update(productItem);
        return productItemDAO.findOne(id);
    }

    @Override
    public ProductItem updateQuantity(int id, int quantity) {
        productItemDAO.updateQuantity(id,quantity);
        return productItemDAO.findOne(id);
    }

    @Override
    public void delete(int id) {
        productItemDAO.delete(id);
    }

    @Override
    public ProductItem findOne(int id) {

        ProductItem productItem = productItemDAO.findOne(id);
        if(productItem==null)return null;
        Product product=productDAO.findOne(productItem.getProductID());
        productItem.setProduct(product);

        productItem.setListProductConfig(productConfigService.findByProductItemID(productItem.getID()));

        return productItem;
    }




    @Override
    public int getTotalItem() {
        return productItemDAO.getTotalItem();
    }

    @Override
    public int getTotalQuantityOfProduct(int productID) {
        return productItemDAO.getTotalQuantityOfProduct(productID);
    }

    @Override
    public ProductItem findOneNotWhereIsDeleted(int id) {
        ProductItem productItem = productItemDAO.findOneNotWhereIsDeleted(id);
        if(productItem==null)return null;
        Product product=productDAO.findOne(productItem.getProductID());
        productItem.setProduct(product);

        productItem.setListProductConfig(productConfigService.findByProductItemID(productItem.getID()));

        return productItem;
    }

    @Override
    public List<ProductItem> getProductItemByProductIDForProductDetail(int productID) {

        List<ProductItem> list= productItemDAO.getProductItemByProductID(productID);
        if(list==null)return null;

        for(ProductItem productItem:list){
            Product product = productDAO.findOne(productItem.getProductID());
            if(product==null)return null;
            productItem.setProduct(product);

            List<ProductConfig> productConfigList = productConfigService.findByProductItemID(productItem.getID());
            if(productConfigList==null)return null;
            productItem.setListProductConfig(productConfigList);
        }

//        //khong lay cai co so luong ton <1
//        List<ProductItem> listNew= new ArrayList<>();
//        for(ProductItem productItem:list){
//            if(productItem.getQuantityInStock()>0){
//                listNew.add(productItem);
//            }
//        }


        return list;

    }
}
