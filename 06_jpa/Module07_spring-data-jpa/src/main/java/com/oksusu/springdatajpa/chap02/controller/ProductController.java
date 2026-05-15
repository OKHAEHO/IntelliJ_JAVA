package com.oksusu.springdatajpa.chap02.controller;


import com.oksusu.springdatajpa.chap02.service.ProductService;
import com.oksusu.springdatajpa.common.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController("chap02-section01-controller")
@RequestMapping("/chap02/section01/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    // === 조회 (Read) ===

    /*
     * ID로 특정 상품 조회 (GET /products/{productId})
     * @param productId URL 경로에서 추출할 상품 ID
     * @return 조회된 상품 정보 (JSON) 또는 404 Not Found
     * */

    @GetMapping("/{productId}")
    // @PathVariable : 웹 브라우저 주소창에 입력된 경로(URL)의 특정 값을 자바 변수로 쏙 가져올 때
    public ResponseEntity<Product> findProductById(@PathVariable("productId") Integer productId) {
        System.out.println("\nController(Chap02) - GET /products/" + productId);
        try {
            Product product = productService.findProductById(productId);
            System.out.println("Controller(Chap02) - 응답데이터 :" + product);
            // ResponseEntitiy.ok() : HTTP 200 OK 상태와 함께 응답 본문에 product 객체를 담아 반환
            return ResponseEntity.ok(product);
        } catch (IllegalArgumentException e) {
            System.err.println("Controller(Chap02) - 상폼 조회 실패 :" + e.getMessage());
            // ResponseEntity.notFound.build() : HTTP 404 Not Found 상태 반환
            return ResponseEntity.notFound().build();
        }
    }


    /*
     * 모든 상품 조회(Get/ products)
     * @return 모든 상품 목록(JSON 배열)
     * */
    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts() {
        System.out.println("\nController(Chap02) - GET /products");
        List<Product> products = productService.findAllProducts();
        System.out.println("Controller(Chap02) - 응답 데이터 (상품 수): " + products.size());
        return ResponseEntity.ok(products);
    }

    /**
     * 📌 특정 가격 이하 상품명 조회 (GET /products/cheap?maxPrice=...)
     *
     * @param maxPrice 요청 파라미터로 전달된 최대 가격
     * @return 조건에 맞는 상품명 목록 (JSON 배열)
     */
    @GetMapping("/cheap") // 예: /products/cheap?maxPrice=10000
    public ResponseEntity<List<String>> findCheapProductNames(@RequestParam("maxPrice") Integer maxPrice) {
        System.out.println("\nController(Chap02) - GET /products/cheap?maxPrice=" + maxPrice);
        List<Product> cheapProducts = productService.findProductsCheaperThan(maxPrice);
        List<String> productNames = cheapProducts.stream()
                .map(Product::getProductName)
                .collect(Collectors.toList()); // toList는 읽기 전용으로 추가, 삭제가 안된다. .collect를 하면 수정이 가능한다.
        System.out.println("Controller(Chap02) - 응답 데이터 (상품명 목록): " + productNames);
        return ResponseEntity.ok(productNames);
    }

    // create 생성
    /*
     * 새로운 상품 등록(Post /products)
     * @param new Product 요청 본문 (Json) 에서 변환된 Product 객체
     * @return 생성된 상품 정보(json)와 HTTP 201 Created 상태 반환
     * */
    @PostMapping // HTTP POST request 처리
    public ResponseEntity<Product> createProduct(@RequestBody Product newProduct) {
        // @RequestBody : 요청 본문의 json 데이터를 product 객체로 변환해준다.
        System.out.println("Controller(Chap02) - POST /products - 새로운 상품 등록 요청 처리");
        Product createdProduct = productService.createProduct(newProduct);
        System.out.println("Controller(Chap02)) - 응답 데이터(생성된 상품) : " + createdProduct);
        /*ResponseEntity.status(HttpStatus.CREATED) : HTTP 201 Created 상태 설정*/
        //.body(createdProduct): 응답 본문에 생성된 상품 정보 포함
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    // update 수정
    /*
     * 특정 상품 정보 수정 (PUT /products/{productId}
     * @param productId 수정할 상품의 ID(URL 경로 변수)
     * @param updatedProductInfo 요청 본문(JSON) 에서 변환된 수정할 정보가 담긴 PRoduct 객체
     * @return 수정된 상품 정보(json) 또는 404 not found
     * */

    @PutMapping("/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable("productId") Integer productId, @RequestBody Product updatedProductInfo) {
        System.out.println("\nController(Chap02 - put /products/ " + productId + "요청 본문 :" + updatedProductInfo);
        try {
            //Service 메서드는 수정된 엔티티를 반환하도록 구현됨(변경 감지 활용)
            Product updatedProduct = productService.updateProduct(
                    productId,
                    updatedProductInfo.getProductName(),
                    updatedProductInfo.getPrice()
            );
            System.out.println("Controller(Chap02 - 응답 데이터(수정된 상품) : " + updatedProduct);
            return ResponseEntity.ok(updatedProduct);
        } catch (IllegalArgumentException e) {
            //Service에서 수정할 상품을 찾지 못한 경우
            System.err.println("Controller(Chap02 - 수정 실패 : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }

    // delete 삭제

    /*
     * 특정 상품 삭제 (DELETE /products/{productId}
     * @param productId 삭제할 상품의 ID(URL 경로 변수)
     * @return 204 No Content 또는 404 Not Found
     * */
    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Integer productId) {
        System.out.println("\nController(Chap02 - DELETE /products/" + productId);
        try {
            productService.deleteProduct(productId);
            System.out.println("Controller(Chap02) - 상품 삭제 성공");
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            System.err.println("Controller(Chap02 - 삭제 실패 : " + e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }
}
