package com.oksusu.springdatajpa.chap04.controller;


import com.oksusu.springdatajpa.chap04.model.ProductDTO;
import com.oksusu.springdatajpa.chap04.service.ProductService;
import com.oksusu.springdatajpa.common.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController("chap04-productController")
@RequestMapping("/products-query") // 동일한 경로 사용
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // --- 쿼리 메소드 사용 API (Section 01) ---
    // ... (findProductsByName 등 생략) ...

    // === @Query 사용 API 예시 ===

    /**
     * 📌 특정 가격 미만 + 가격 오름차순 조회 (GET /products-query/price-below-sorted?maxPrice=...)
     */
    @GetMapping("/price-below-sorted")
    public ResponseEntity<List<Product>> findProductsBelowPriceSorted(@RequestParam("maxPrice") Integer maxPrice) {
        System.out.println("\nController(Chap03) - GET /price-below-sorted?maxPrice=" + maxPrice);
        List<Product> products = productService.findProductsBelowPriceSorted(maxPrice); // ✅ @Query(JPQL) 호출!
        System.out.println("Controller(Chap03) - 응답 데이터 수: " + products.size());
        return ResponseEntity.ok(products);
    }

    /**
     * 📌 특정 가격 이상 상품 DTO 조회 (GET /products-query/dto/price-above?minPrice=...)
     */
    @GetMapping("/dto/price-above")
    public ResponseEntity<List<ProductDTO>> findProductDTOsAbovePrice(@RequestParam("minPrice") Integer minPrice) {
        System.out.println("\nController(Chap03) - GET /dto/price-above?minPrice=" + minPrice);
        List<ProductDTO> productDTOs = productService.findProductDTOsAbovePrice(minPrice); // ✅ @Query(JPQL+DTO) 호출!
        System.out.println("Controller(Chap03) - 응답 데이터(DTO) 수: " + productDTOs.size());
        return ResponseEntity.ok(productDTOs); // DTO 목록 반환
    }

    /**
     * 📌 Native Query로 이름, 가격 조회 (GET /products-query/native/name-price?minPrice=...)
     * 🚨 주의: Native Query 결과(Object[])는 JSON으로 자동 변환 시 문제가 생길 수 있으므로,
     * Controller에서 DTO 등으로 가공하여 반환하는 것이 좋습니다. 여기서는 예시로 Object[] 리스트 반환.
     */
    @GetMapping("/native/name-price")
    public ResponseEntity<List<Object[]>> findProductNameAndPriceNative(@RequestParam("minPrice") Integer minPrice) {
        System.out.println("\nController(Chap03) - GET /native/name-price?minPrice=" + minPrice);
        List<Object[]> results = productService.findProductNameAndPriceNative(minPrice); // ✅ @Query(Native) 호출!
        System.out.println("Controller(Chap03) - 응답 데이터(Object[]) 수: " + results.size());
        // results.forEach(row -> System.out.println("  - Name: " + row[0] + ", Price: " + row[1]));
        return ResponseEntity.ok(results);
    }


}