package com.oksusu.inheritance.section03;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clothing_product_joined")
@DiscriminatorValue("Clothing")
public class ClothingProductJoined extends ProductJoined {

    @Column(name = "size")
    private String size;
    @Column(name = "material")
    private String material;
    @Column(name = "color")
    private String color;

    protected ClothingProductJoined() {
    }

    public ClothingProductJoined(String name, int price, String brand, int stockQuantity, String size, String material, String color) {
        super(name, price, brand, stockQuantity);
        this.size = size;
        this.material = material;
        this.color = color;
    }

    @Override
    public String toString() {
        return "ClothingProductJoined{" +
                "size='" + size + '\'' +
                ", material='" + material + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
