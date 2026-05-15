package com.oksusu.inheritance.section03;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("Electornic")
@Table(name = "electronic_product_joined")
public class ElectronicProductJoined extends ProductJoined {

    @Column(name = "warranty_period")
    private int warrantyPeriod;
    @Column(name = "power_consumption")
    private String powerConsumption;

    protected ElectronicProductJoined() {
    }

    public ElectronicProductJoined(String name, int price, String brand, int stockQuantity, int warrantyPeriod, String powerConsumption) {
        super(name, price, brand, stockQuantity);
        this.warrantyPeriod = warrantyPeriod;
        this.powerConsumption = powerConsumption;
    }

    @Override
    public String toString() {
        return "ElectronicProductJoined{" +
                "warrantyPeriod=" + warrantyPeriod +
                ", powerConsumption='" + powerConsumption + '\'' +
                '}';
    }
}
