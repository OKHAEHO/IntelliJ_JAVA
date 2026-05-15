package com.oksusu.jpql.chap04.repository;

import org.example.springdatajpa.common.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("chap04-repository") // 안써도 상관ㅇ없지만 관례사ㅇ 쓴다.
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.price < :maxPrice order by p.price ASC")
    List<Product> findProductBelowPriceSorted(@Param("maxPrice") Integer maxPrice);

    @Query("SELECT p FROM Product p WHERE p.productName like %?1%")
    List<Product> searchProductByNameKeyword(String keyword);

    @Query("SELECT new com.oksusu.springdatajpa.chap04.model.ProductDTO(p.productId, p.productName, p.price) " +
            " FROM Product p where p.price >= :minPrice")
    List<Product> findProductDTOAbovePrice(@Param("minPrice") Integer minPrice);


}
