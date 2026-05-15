package com.oksusu.jpql.chap04.model;

public class ProductDTO {
    private Integer productId;
    private String productName;
    private Integer price;

    public ProductDTO(Integer productId, String productName, Integer price) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }

    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getPrice() {
        return price;
    }
}
