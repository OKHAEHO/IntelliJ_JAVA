package com.oksusu.springdatajpa.chap03.service;


import com.oksusu.springdatajpa.chap03.repository.ProductRepository;
import com.oksusu.springdatajpa.common.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("chap03-productService")
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        System.out.println("ProductService(Chap03) 생성: ProductRepository 주입됨");
        this.productRepository = productRepository;
    }

    // --- 쿼리 메소드 호출 예시 ---

    @Transactional(readOnly = true)
    public List<Product> findProductsByName(String productName) {
        System.out.println("Service(Chap03) - findProductsByName 호출: productName = " + productName);
        List<Product> products = productRepository.findByProductName(productName); // ✅ 쿼리 메소드 호출!
        System.out.println("Service(Chap03) - 조회된 상품 수: " + products.size());
        return products;
    }

    @Transactional(readOnly = true)
    public List<Product> findProductsPriceGreaterThan(Integer price) {
        System.out.println("Service(Chap03) - findProductsPriceGreaterThan 호출: price = " + price);
        List<Product> products = productRepository.findByPriceGreaterThan(price); // ✅ 쿼리 메소드 호출!
        System.out.println("Service(Chap03) - 조회된 상품 수: " + products.size());
        return products;
    }

    @Transactional(readOnly = true)
    public List<Product> findProductsByNameContaining(String keyword) {
        System.out.println("Service(Chap03) - findProductsByNameContaining 호출: keyword = " + keyword);
        List<Product> products = productRepository.findByProductNameContaining(keyword); // ✅ 쿼리 메소드 호출!
        System.out.println("Service(Chap03) - 조회된 상품 수: " + products.size());
        return products;
    }

    @Transactional(readOnly = true)
    public List<Product> findProductsCheaperThanAndSort(Integer price) {
        System.out.println("Service(Chap03) - findProductsCheaperThanAndSort 호출: price = " + price);
        // Sort 객체 생성: Sort.by("필드명").descending() / .ascending()
        Sort sort = Sort.by("price").descending(); // 가격 내림차순 정렬
        List<Product> products = productRepository.findByPriceLessThan(price, sort); // ✅ 정렬 파라미터 전달!
        System.out.println("Service(Chap03) - 조회된 상품 수: " + products.size());
        return products;
    }

    @Transactional(readOnly = true)
    public List<Product> findProductsByIds(List<Integer> productIds) {
        System.out.println("Service(Chap03) - findProductsByIds 호출: productIds = " + productIds);
        List<Product> products = productRepository.findByProductIdIn(productIds); // ✅ In 조건 쿼리 메소드 호출!
        System.out.println("Service(Chap03) - 조회된 상품 수: " + products.size());
        return products;
    }

}