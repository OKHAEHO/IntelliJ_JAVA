package com.oksusu.springdatajpa.chap04.service;


import com.oksusu.springdatajpa.chap04.model.ProductDTO;
import com.oksusu.springdatajpa.chap04.repository.ProductRepository;
import com.oksusu.springdatajpa.common.Product;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("chap04-productService")
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // --- 쿼리 메소드 활용 예시 (Section 01) ---
    // ... (findProductsByName 등 생략) ...

    // === @Query 활용 예시 ===

    @Transactional(readOnly = true)
    public List<Product> findProductsBelowPriceSorted(Integer maxPrice) {
        System.out.println("Service(Chap03) - findProductsBelowPriceSorted 호출: maxPrice = " + maxPrice);
        List<Product> products = productRepository.findProductsBelowPriceSorted(maxPrice); // ✅ @Query(JPQL) 호출!
        System.out.println("Service(Chap03) - 조회된 상품 수: " + products.size());
        return products;
    }

    @Transactional(readOnly = true)
    public List<ProductDTO> findProductDTOsAbovePrice(Integer minPrice) {
        System.out.println("Service(Chap03) - findProductDTOsAbovePrice 호출: minPrice = " + minPrice);
        List<ProductDTO> productDTOs = productRepository.findProductDTOAbovePrice(minPrice); // ✅ @Query(JPQL+DTO) 호출!
        System.out.println("Service(Chap03) - 조회된 DTO 수: " + productDTOs.size());
        return productDTOs;
    }

    @Transactional(readOnly = true)
    public List<Object[]> findProductNameAndPriceNative(Integer minPrice) {
        System.out.println("Service(Chap03) - findProductNameAndPriceNative 호출: minPrice = " + minPrice);
        List<Object[]> results = productRepository.findProductNameAndPriceNative(minPrice); // ✅ @Query(Native) 호출!
        System.out.println("Service(Chap03) - 조회된 결과 수: " + results.size());
        // 결과 처리 예시 (Object[] -> DTO 변환 등)
        // results.forEach(row -> System.out.println("Name: " + row[0] + ", Price: " + row[1]));
        return results;
    }


    @Transactional(readOnly = true)
    public Product findProductById(Integer productId) {
        // Chap02의 findProductById 구현 재사용
        return productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("ID " + productId + " 상품 없음"));
    }
}