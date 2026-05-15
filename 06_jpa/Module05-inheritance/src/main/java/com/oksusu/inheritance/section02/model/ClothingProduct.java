package com.oksusu.inheritance.section02.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Clothing")
public class ClothingProduct extends Product {
    /*  product에서 상속 받기 떄문에 없어도 된다.
        private String name;
        private double price;
        private String brand;
        private int stockQuantity;
        */
    @Column(name = "size")
    private String size;
    @Column(name = "material")
    private String material;
    @Column(name = "color")
    private String color;

    protected ClothingProduct() {
    }

    public ClothingProduct(String name, int price, String brand, int stockQuantity, String size, String material, String color) {
        super(name, price, brand, stockQuantity);
        this.size = size;
        this.material = material;
        this.color = color;
    }

    @Override
    public String toString() {
        return "ClothingProduct{" +
                "size='" + size + '\'' +
                ", material='" + material + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
