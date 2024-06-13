package com.EcommerceWeb.dao.impl;

import java.util.List;

import com.EcommerceWeb.dao.IProductDAO;
import com.EcommerceWeb.mapper.ProductCategoryMapper;
import com.EcommerceWeb.mapper.ProductItemMapper;
import com.EcommerceWeb.mapper.ProductMapper;
import com.EcommerceWeb.model.Product;
import com.EcommerceWeb.model.ProductCategory;
import com.EcommerceWeb.model.ProductItem;

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
		String sql2 = "UPDATE ProductItem SET IsDeleted = true WHERE ProductID = ?";
		update(sql2, id);
	}
	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM Product";
		return count(sql);
	}

	public double getMinPrice(int id) {
		String sql = "SELECT * FROM productitem WHERE productid = ? ORDER BY price DESC LIMIT 1;";
		List<ProductItem> productItems = query(sql, new ProductItemMapper(), id);
		if(productItems.isEmpty()) return 0;
		return productItems.get(0).getPrice();
	}
	public double getMaxPrice(int id) {
		String sql = "SELECT * FROM productitem WHERE productid = ? ORDER BY price DESC LIMIT 1;";
		List<ProductItem> productItems = query(sql, new ProductItemMapper(), id);
		if(productItems.isEmpty()) return 0;
		return productItems.get(0).getPrice();
	}
	public int getTotalQuantityInStock(int id) {
		String sql="SELECT sum(QuantityInStock) FROM productitem WHERE ProductID = ? and IsDeleted = 0";
		return count(sql, id);
	}
	public ProductCategory getCategory(int id) {
		String sql="SELECT * FROM productcategory WHERE ID = ? and IsDeleted = 0";
		List<ProductCategory> productCategories = query(sql, new ProductCategoryMapper(), id);
		return productCategories.get(0);
	}

	@Override
	public List<ProductItem> getProductItems(int id) {
		String sql = "SELECT * FROM productitem WHERE productid = ? and IsDeleted=0";
		List<ProductItem> productItems = query(sql, new ProductItemMapper(), id);
		return productItems;
	}

	public List<Product> top3saleProduct() {
		String sql = "SELECT p.*\r\n"
				+ "FROM product p\r\n"
				+ "INNER JOIN productitem pi ON p.id = pi.productid\r\n"
				+ "INNER JOIN orderline ol ON pi.id = ol.productitemid\r\n"
				+ "GROUP BY p.id\r\n"
				+ "ORDER BY SUM(ol.quantity) DESC\r\n"
				+ "LIMIT 3;";
		return query(sql, new ProductMapper());
	}
	public int count() {
		String sql = "SELECT count(*) FROM product where IsDeleted=0";
		return count(sql);
	}
	public ProductItem findItemByVariation(int id, int color, int size)
	{
		String sql="SELECT * FROM productitem pi WHERE ProductID = ? and IsDeleted=0 and " +
				"(SELECT count(*) FROM productconfig WHERE ProductItemID=pi.ID and (VariationID=? or VariationID=?) and IsDeleted=0)=2";
		List<ProductItem> productItems = query(sql, new ProductItemMapper(), id,color,size);
		return productItems.isEmpty() ? null : productItems.get(0);
	}

	public ProductItem findItemByOneVariation(int id, int size, int color) {
		String sql="SELECT * FROM productitem pi WHERE ProductID = ? and IsDeleted=0 and  EXISTS " +
				"(SELECT * FROM productconfig pg WHERE pg.ProductItemID=pi.ID and (pg.VariationID=? or pg.VariationID=?) and pg.IsDeleted=0 and  " +
				"(SELECT count(*) FROM productconfig pg2 WHERE pg2.ProductItemID=pi.ID and (pg2.VariationID!=pg.VariationID) and pg2.IsDeleted=0)<2)";
		List<ProductItem> productItems = query(sql, new ProductItemMapper(), id,color,size);
		return productItems.isEmpty() ? null : productItems.get(0);
	}

}
