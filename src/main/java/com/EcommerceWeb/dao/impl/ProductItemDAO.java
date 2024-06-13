package com.EcommerceWeb.dao.impl;

import com.EcommerceWeb.dao.IProductItemDAO;
import com.EcommerceWeb.mapper.ProductItemMapper;
import com.EcommerceWeb.mapper.ProductMapper;
import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductItem;

import java.util.List;

public class ProductItemDAO extends AbstractDAO<ProductItem> implements IProductItemDAO {
    @Override
    public List<ProductItem> getAll() {
        String sql = "SELECT * FROM ProductItem WHERE IsDeleted = 0";
        return query(sql, new ProductItemMapper());
    }

    @Override
    public List<ProductItem> getAllisDelete() {
        String sql = "SELECT * FROM ProductItem";
        return query(sql, new ProductItemMapper());
    }

    @Override
    public List<ProductItem> getProductItemByProductID(int id) {
        String sql = "SELECT * FROM ProductItem WHERE ProductID = ? and IsDeleted = 0";
        return query(sql, new ProductItemMapper(),id);
    }

    @Override
    public int add(ProductItem productItem) {
        String sql = "INSERT INTO ProductItem (ProductID, SKU, QuantityInStock, ProductImage, Price) \r\n"
                + "VALUES (?, ?, ?, ?, ?)";
        return insert(sql, productItem.getProductID(), productItem.getSKU(),
                productItem.getQuantityInStock(), productItem.getProductImage(),productItem.getPrice());
    }

    @Override
    public void update(ProductItem productItem) {
        String sql = "UPDATE ProductItem "
                + "SET ProductID = ?, SKU = ?, QuantityInStock = ?, ProductImage = ?, Price = ? "
                + "WHERE ID = ?";

        update(sql ,productItem.getProductID(), productItem.getSKU(), productItem.getQuantityInStock(), productItem.getProductImage(), productItem.getPrice(), productItem.getID());
    }

    @Override
    public void updateQuantity(int id, int quantity) {
        String sql = "UPDATE ProductItem "
                + "SET QuantityInStock = ? "
                + "WHERE ID = ?";
        update(sql, quantity, id);
    }

    @Override
    public void delete(int id) {
        String sql = "UPDATE ProductItem SET IsDeleted = true WHERE ID = ?";
        update(sql, id);
    }

    @Override
    public ProductItem findOne(int id) {
        String sql = "SELECT * FROM productitem WHERE ID = ? and IsDeleted = 0";
        List<ProductItem> productItems = query(sql, new ProductItemMapper(), id);
        return productItems.isEmpty() ? null : productItems.get(0);
    }
    public ProductItem findOnee(int id) {
        String sql = "SELECT * FROM productitem WHERE ID = ?";
        List<ProductItem> productItems = query(sql, new ProductItemMapper(), id);
        return productItems.isEmpty() ? null : productItems.get(0);
    }

    @Override
    public ProductItem findOneNotWhereIsDeleted(int id) {
        String sql = "SELECT * FROM productitem WHERE ID = ?";
        List<ProductItem> productItems = query(sql, new ProductItemMapper(), id);
        return productItems.isEmpty() ? null : productItems.get(0);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM ProductItem";
        return count(sql);
    }

    @Override
    public int getTotalQuantityOfProduct(int productID) {
        String sql = "SELECT SUM(QuantityInStock) AS TotalQuantity FROM ProductItem WHERE ProductID = ?";
        return count(sql,productID);
    }

}
