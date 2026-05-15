package com.oksusu.inheritance.section01;

/*
 * 상속 매핑이 없으면 발생하는 문제
 * - 온라인 쇼핑몰에서 상품(Product)를 관리한다고 가정하자.
 * - 상품에는 전자제품, 의류, 식품이 존재한다.
 * - 상속 없이 각각의 상품을 별도의 클래스로 관리하면 중복코드가 발생하고, 공통 속성을 관리하기 어렵다.
 * - 또한, db table도 각각 별도로 생성해야하므로 테이블 간의 관계 관리가 복잡해진다.
 * */


import com.oksusu.inheritance.section01.model.ClothingProduct;
import com.oksusu.inheritance.section01.model.ElectronicProduct;
import com.oksusu.inheritance.section01.model.FoodProduct;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        ElectronicProduct electronic = new ElectronicProduct("Laptop", 999.99, "TechBrand", 50, 24);
        ClothingProduct clothing = new ClothingProduct("T-Shirt", 19.99, "FashionBrand", 100, "M", "Cotton");
        FoodProduct food = new FoodProduct("Milk", 2.99, "FoodBrand", 200, LocalDate.now().plusDays(7), true);

        System.out.println(electronic);
        System.out.println(clothing);
        System.out.println(food);

        // 상속을 사용하지 않음녀 공통 속성을 관리하기 위한 추가 로직이 필요함

        /*
         * 문제점 : name, price, brand, stockQuantity가 중복 정의됨
         * db에서도 electronic_products, clothing_products, food_products table을 별도로 생성해야함.
         * 공통 속성을 조회하려면 세 테이블을 각각 조회해야하므로 쿼리 복잡도가 증가한다.
         * 새로운 상품 유형이 추가될 때마다 새로운 클래스를 만들어야함
         **/

        /*
         * 상속 매핑을 사용해서 해결한다.
         * - 상속을 사용하면 공통 속성을 부모 클래스(product)에 정의하고, 자식 클래스에서 특화된 속성만 정의.
         * -jpa의 상속 매핑 전략을 사용하면 db table 설계도 유연해진다.
         * */

    }


}
