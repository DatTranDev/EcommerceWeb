package com.EcommerceWeb.dao.impl;
import java.util.List;
import com.EcommerceWeb.dao.IProductCategoryDAO;
import com.EcommerceWeb.mapper.ProductCategoryMapper;
import com.EcommerceWeb.mapper.ProductMapper;
import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductCategory;
public class ProductCategoryDAO extends AbstractDAO<ProductCategory> implements IProductCategoryDAO {

    @Override
    public List<ProductCategory> getAll(){
        String sql = "SELECT * FROM productcategory WHERE IsDeleted = 0 and ParentCategoryID is not null";
        return query(sql, new ProductCategoryMapper());
    }
    public List<ProductCategory> getByParentCategoryID(int parentCategoryID){
        String sql = "SELECT * FROM ProductCategory WHERE IsDeleted = 0 and ParentCategoryID = ?";
        return query(sql, new ProductCategoryMapper(), parentCategoryID);
    }
    @Override
    public int add(ProductCategory productCategory) {
        String sql = "INSERT INTO ProductCategory (ParentCategoryID, CategoryName) \r\n"
                + "VALUES (?, ?)";
        return insert(sql, productCategory.getParentCategoryID(), productCategory.getCategoryName());
    }
    @Override
    public void update(ProductCategory productCategory) {
        String sql = "UPDATE ProductCategory SET CategoryName = ? WHERE ID = ?";
        update(sql,productCategory.getCategoryName(), productCategory.getID());
    }

    @Override
    public boolean delete(int id) {
        String sql1 = "SELECT * FROM Product WHERE IsDeleted = 0 and CategoryID = ?";
        List<Product> list = query(sql1, new ProductMapper(), id);
        if(list==null || list.isEmpty())
        {
            String sql2= "UPDATE ProductCategory SET IsDeleted = true WHERE ID = ?";
            update(sql2, id);
            return true;
        }
        else
        {
            return false;
        }

    }

    @Override
    public ProductCategory findOne(int id) {
        String sql = "SELECT * FROM ProductCategory WHERE ID = ? and IsDeleted = 0";
        List<ProductCategory> productCategories = query(sql, new ProductCategoryMapper(), id);
        return productCategories.isEmpty() ? null : productCategories.get(0);
    }

    @Override
    public List<ProductCategory> getAllParent() {
        String sql = "SELECT * FROM ProductCategory WHERE ParentCategoryID is null and IsDeleted = 0";
        List<ProductCategory> productCategories = query(sql, new ProductCategoryMapper());
        return productCategories;
    }


}
