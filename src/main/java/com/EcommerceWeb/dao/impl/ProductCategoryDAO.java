package com.EcommerceWeb.dao.impl;
import java.util.List;
import com.EcommerceWeb.dao.IProductCategoryDAO;
import com.EcommerceWeb.mapper.ProductCategoryMapper;
import com.EcommerceWeb.model.ProductCategory;
public class ProductCategoryDAO extends AbstractDAO<ProductCategory> implements IProductCategoryDAO {

    @Override
    public List<ProductCategory> getAll(){
        String sql = "SELECT * FROM ProductCategory WHERE IsDeleted = 0";
        return query(sql, new ProductCategoryMapper());
    }

    @Override
    public int add(ProductCategory productCategory) {
        String sql = "INSERT INTO ProductCategory (ParentCategoryID, CategoryName) \r\n"
                + "VALUES (?, ?)";
        return insert(sql, productCategory.getParentCategoryID(), productCategory.getCategoryName());
    }
    @Override
    public void update(ProductCategory productCategory) {
        String sql = "UPDATE ProductCategory SET ParentCategoryID = ?, DisplayName = ? WHERE ID = ?";

        update(sql, productCategory.getParentCategoryID(), productCategory.getCategoryName(), productCategory.getID());
    }

    @Override
    public void delete(int id) {
        String sql = "UPDATE ProductCategory SET IsDeleted = true WHERE ID = ?";
        update(sql, id);
    }

    @Override
    public ProductCategory findOne(int id) {
        String sql = "SELECT * FROM ProductCategory WHERE ID = ? and IsDeleted = 0";
        List<ProductCategory> productCategories = query(sql, new ProductCategoryMapper(), id);
        return productCategories.isEmpty() ? null : productCategories.get(0);
    }
    
}
