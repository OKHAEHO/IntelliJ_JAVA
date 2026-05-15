package com.oksusu.springdatajpa.chap04.repository;

import com.oksusu.springdatajpa.chap04.model.ProductDTO;
import com.oksusu.springdatajpa.common.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("chap04-repository") // 안써도 상관ㅇ없지만 관례사ㅇ 쓴다.
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p WHERE p.price < :maxPrice order by p.price ASC")
    List<Product> findProductsBelowPriceSorted(@Param("maxPrice") Integer maxPrice);

    @Query("select p from Product p where p.productName like %?1%")
    List<Product> searchProductsByNameKeyword(String keyword);

    @Query("SELECT new com.oksusu.springdatajpa.chap04.model.ProductDTO(p.productId, p.productName, p.price) " +
            " FROM Product p where p.price >= :minPrice")
    List<ProductDTO> findProductDTOAbovePrice(@Param("minPrice") Integer minPrice);

    //jpql 을 쓰면 sql에서 제공하는 내장함수를 쓸 수 없었따.
    @Query(
            value = "SELECT product_name, price FROM tbl_product WHERE price > ?1",
            nativeQuery = true
    )
    List<Object[]> findProductNameAndPriceNative(Integer minPrice);

}
