package com.example.virtualshop;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "product_table")
public class Product {
    @PrimaryKey(autoGenerate = true)
    private int productId;

    private String productName;
    private double productPrice;

    // Constructors, getters, and setters

    public Product(String productName, double productPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
