package com.oksusu.springdatajpa.common;

import jakarta.persistence.*;

/*
 * 📌 Product 엔티티
 * - 상품 정보를 나타내는 간단한 엔티티입니다.
 * - 상품 ID (PK), 상품명, 가격 필드를 가집니다.
 * - JPA 어노테이션을 사용하여 데이터베이스 테이블과 매핑합니다.
 */
@Entity // 이 클래스가 JPA 엔티티임을 선언
@Table(name = "tbl_product") // 'tbl_product' 테이블과 매핑
public class Product {

    @Id // 기본 키(Primary Key) 필드임을 선언
    @Column(name = "product_id") // 'product_id' 컬럼과 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성 전략: DB의 auto_increment 사용
    private Integer productId;

    @Column(name = "product_name", nullable = false) // 'product_name' 컬럼, NOT NULL 제약 조건
    private String productName;

    @Column(name = "price", nullable = false) // 'price' 컬럼, NOT NULL 제약 조건
    private Integer price;

    // JPA는 기본 생성자를 요구합니다 (protected 또는 public).
    protected Product() {
    }

    // 초기화 생성자
    public Product(String productName, Integer price) {
        this.productName = productName;
        this.price = price;
    }

    // Getter 메서드들...
    public Integer getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    // (Setter는 필요에 따라 추가 - 예: 업데이트 기능)
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }
}