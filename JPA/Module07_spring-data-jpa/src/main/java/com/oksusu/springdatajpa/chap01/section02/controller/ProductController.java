package com.oksusu.springdatajpa.chap01.section02.controller;

import com.oksusu.springdatajpa.chap01.section02.service.ProductService;
import com.oksusu.springdatajpa.common.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController("chap01Section02ProductController") // 웹 사이트를 안만들거라 이렇게 하는거지 Controller를 써서 쓰는ㄱㅔ 조타
//사용자에게 요청을 받고 돌려주는 곳이다 둘의 차이는 응답갓ㅄ을 제이슨으로 하겠따 기본은 hml로 하게따
// 모바일은 rest 웹은 그냥

// 응답을 get으로 받는 요청 메서드
@RequestMapping("/chap01/section02")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/cheap")
    @ResponseBody // 처음보는 애, 응답을 해줄 때 응답의 형식에 맞ㄹ춰서 응답해줘야한다
    // status , header, body 매번 응답시마다 쓴느게 귀찮아서 축약해서 쓴느 어노테이션 리턴된 값만 바인딩해서 보내준다.
    public List<String> findCheapProductNames(@RequestParam("maxPrice") Integer maxPrice) {
        System.out.println("controler findcheapproductname : " + maxPrice);

        List<Product> cheapProducts = productService.findProductCheaperThan(maxPrice);

        List<String> productNames = cheapProducts.stream()
                .map(Product::getProductName)
                .collect(Collectors.toList());
        System.out.println("응답할 상품명 : " + productNames);
        //안쓰게 된다면
        return productNames;
    }

    @GetMapping("/products/{productId}")
    public Product findProductById(@PathVariable("productId") Integer productId) {
        System.out.println("Controller - findProductById : " + productId);
        Product product = productService.findProductById(productId);
        return product;
    }
}
