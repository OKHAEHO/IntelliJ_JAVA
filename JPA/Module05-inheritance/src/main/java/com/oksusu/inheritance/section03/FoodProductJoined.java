package com.oksusu.inheritance.section03;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("Food")
@Table(name = "food_product_joined")
public class FoodProductJoined extends ProductJoined {

    private LocalDate expirationDate;
    private boolean isOrganic;

    protected FoodProductJoined() {
    }

    @Override
    public String toString() {
        return "FoodProductJoined{" +
                "expirationDate=" + expirationDate +
                ", isOrganic=" + isOrganic +
                '}';
    }
}
