package com.oksusu.inheritance.section03;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // 상속 싱글 테이블
@DiscriminatorColumn(name = "product_type")
@Table(name = "products_joined")
public class ProductJoined {
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

    protected ProductJoined() {
    }

    public ProductJoined(String name, int price, String brand, int stockQuantity) {
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
