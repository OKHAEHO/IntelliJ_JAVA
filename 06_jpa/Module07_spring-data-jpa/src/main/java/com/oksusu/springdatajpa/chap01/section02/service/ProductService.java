package com.oksusu.springdatajpa.chap01.section02.service;


import com.oksusu.springdatajpa.chap01.section02.repository.ProductRepository;
import com.oksusu.springdatajpa.common.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service // 비지니스로직을 담당하는 곳이다 . 서비스로직이 응집되어있는 곳
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // 그럼 수정을 하면 어떻게 되는가?
    @Transactional(readOnly = true) // 트렌젝션 오너테이션 서비스에서 주로 사용되는 어노테이션
    // 리드온리트루를 왜 줬냐 jpa는 1차캐싱에서 persist에서 selct 전용 다른 업데이트 딜리트가 있으면 트루를 주면 안댄다
    public List<Product> findProductCheaperThan(Integer maxPrice) {
        System.out.println("Max price : " + maxPrice);
        List<Product> allProducts = productRepository.findAllProducts();

        List<Product> filteredProducts = allProducts.stream()
                .filter(product -> product.getPrice() <= maxPrice)
                .toList();
        System.out.println("필터링된 상품 수: " + filteredProducts.size());
        return filteredProducts;
    }

    @Transactional(readOnly = true)
    public Product findProductById(Integer id) {
        System.out.println("service findProduct 호출 :" + id);
        return productRepository.findProductById(id);
    }
}
