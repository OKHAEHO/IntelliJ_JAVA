package com.oksusu.inheritance.section02.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Food")
public class FoodProduct extends Product {

    private LocalDate expirationDate;
    private boolean isOrganic;

    protected FoodProduct() {
    }

    @Override
    public String toString() {
        return "FoodProduct{" +
                "expirationDate=" + expirationDate +
                ", isOrganic=" + isOrganic +
                '}';
    }
}
