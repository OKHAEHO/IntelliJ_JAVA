package com.oksusu.springdatajpa.chap03.repository;

import com.oksusu.springdatajpa.common.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("chap03-repository") // 안써도 상관ㅇ없지만 관례사ㅇ 쓴다.
public interface ProductRepository extends JpaRepository<Product, Integer> {
    /*
     * 상품 이름으로 목록 조회하기
     * */
    List<Product> findByProductName(String productName); // 이름 중복 때문에 list로

    /*
     * 특정 가격보다 비싼 상품 조회하기
     * price >= :price 로 구성됨
     * */
    List<Product> findByPriceGreaterThan(int price);

    /*
     * 특정 가격보다 낮은 상품 목록 조회 및 정렬 조건 추가.
     * price < :price Orderby price ASC 로 설정함
     * */
    List<Product> findByPriceLessThan(int price, Sort sort);

    /*
     * 키워드가 존재하는지
     * */

    List<Product> findByProductNameContaining(String keyword);

    /*
     * 여러 사ㅇ품 ID 목록에 해당하는 상품 목록 조회
     * */
    List<Product> findByProductIdIn(List<Integer> productIds);

    /*
     * 특정 가격 범위 내의 상품 목록 조회
     * */
    List<Product> findByPriceBetween(Integer priceAfter, Integer priceBefore);

    /*
     * 상품 이름으로 조회 ++ 가격 내림 차순
     * */
    List<Product> findByProductNameOrderByPriceDesc(String productName);

}
