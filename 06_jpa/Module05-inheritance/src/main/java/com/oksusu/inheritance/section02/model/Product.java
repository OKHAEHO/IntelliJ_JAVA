package com.oksusu.inheritance.section02.model;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 상속 싱글 테이블
@DiscriminatorColumn(name = "product_type")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private int price;
    @Column(name = "brand")
    private String brand;
    @Column(name = "stock_quantity")
    private int stockQuantity;

    protected Product() {
    }

    public Product(String name, int price, String brand, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                ", stockQuantity=" + stockQuantity +
                '}';
    }
}
