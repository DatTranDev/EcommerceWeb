package com.EcommerceWeb.model;

import java.util.List;

public class ProductItem {
    private int ID;
    private int productID;
    private String SKU;
    private int quantityInStock;
    private String productImage;
    private double price;
    private boolean isDeleted;

    private Product product;
    private List<ProductConfig> listProductConfig;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<ProductConfig> getListProductConfig() {
        return listProductConfig;
    }

    public void setListProductConfig(List<ProductConfig> listProductConfig) {
        this.listProductConfig = listProductConfig;
    }
}

