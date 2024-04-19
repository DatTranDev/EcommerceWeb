package com.EcommerceWeb.dao.impl;

import java.util.List;

import com.EcommerceWeb.dao.IProductDAO;
import com.EcommerceWeb.mapper.ProductMapper;
import com.EcommerceWeb.model.Product;

public class ProductDAO extends AbstractDAO<Product> implements IProductDAO {
	
	@Override
	public List<Product> getAll(){
		String sql = "SELECT * FROM Product WHERE IsDeleted = 0";
		return query(sql, new ProductMapper());
		
	}
	@Override
	public Product findOne(int id) {
		String sql = "SELECT * FROM Product WHERE ID = ? and IsDeleted = 0";
		List<Product> products = query(sql, new ProductMapper(), id);
		return products.isEmpty() ? null : products.get(0);
	}
	
	@Override
	public List<Product> getProductByCategory(int id) {
	    String sql = "WITH RECURSIVE CategoryHierarchy AS (\r\n"
	    		+ "    SELECT ID\r\n"
	    		+ "    FROM ProductCategory\r\n"
	    		+ "    WHERE ID = ? -- Start with the provided category ID\r\n"
	    		+ "    UNION ALL\r\n"
	    		+ "    SELECT pc.ID\r\n"
	    		+ "    FROM ProductCategory pc\r\n"
	    		+ "    INNER JOIN CategoryHierarchy ch ON pc.ParentCategoryID = ch.ID\r\n"
	    		+ ")\r\n"
	    		+ "SELECT p.*\r\n"
	    		+ "FROM Product p\r\n"
	    		+ "INNER JOIN CategoryHierarchy ch ON p.CategoryID = ch.ID\r\n"
	    		+ "WHERE p.IsDeleted = 0;";
	    return query(sql, new ProductMapper(), id);
	    // Open connection
	}
	@Override
	public int add(Product product) {
		String sql = "INSERT INTO Product (CategoryID, DisplayName, Description, ProductImage) \r\n"
				+ "VALUES (?, ?, ?, ?)";
		return insert(sql, product.getCategoryID(), product.getDisplayName(),
						product.getDescription(), product.getProductImage());
	}
	@Override
	public void update(Product product) {
		String sql = "UPDATE Product SET CategoryID = ?, DisplayName = ?,\r\n"
				+ "Description = ?, ProductImage = ? WHERE ID = ?";
		
		update(sql, product.getCategoryID(), product.getDisplayName() , product.getDescription()
				, product.getProductImage(), product.getID());
	}

	@Override
	public void delete(int id) {
		String sql = "UPDATE Product SET IsDeleted = true WHERE ID = ?";
		update(sql, id);
	}
	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM Product";
		return count(sql);
	}

}