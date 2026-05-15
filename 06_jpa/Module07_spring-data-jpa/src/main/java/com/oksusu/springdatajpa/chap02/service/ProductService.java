package com.oksusu.springdatajpa.chap02.service;


import com.oksusu.springdatajpa.chap02.repository.ProductRepository;
import com.oksusu.springdatajpa.common.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("chap02-section01-service") // 비지니스로직을 담당하는 곳이다 . 서비스로직이 응집되어있는 곳
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Product findProductById(Integer id) {
        System.out.println("service findProduct" + id);
        Optional<Product> optional = productRepository.findById(id); // 값이 있을 수도 있고 없을 수도 있따.

        Product product = optional.orElseThrow(() -> new RuntimeException("product not found")); //optional이 없으면 예외를 던진다
        return product;
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProducts() {
        System.out.println("service - findAllProducts");
        List<Product> products = productRepository.findAll();
        System.out.println("전체 상품 수: " + products.size());
        return products;
    }

    @Transactional(readOnly = true)
    public List<Product> findProductsCheaperThan(Integer maxPrice) {
        System.out.println("Max price : " + maxPrice);
        List<Product> allProducts = productRepository.findAll();

        List<Product> filteredProducts = allProducts.stream()
                .filter(product -> product.getPrice() < maxPrice)
                .toList();

        return filteredProducts;
    }

    @Transactional
    public Product createProduct(Product product) {
        System.out.println("service - createProduct 호출 product = " + product);
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Transactional
    public Product updateProduct(Integer id, String newName, Integer newPrice) {
        System.out.println("Service - updateProduct 호출 " + id);
        Product productToUpdate = findProductById(id);

        System.out.println("service 기본 정보 " + productToUpdate);
        productToUpdate.setProductName(newName);
        productToUpdate.setPrice(newPrice);
        System.out.println("service 업데이트 정보 " + productToUpdate);

        productRepository.save(productToUpdate);
        return productToUpdate;
    }

    @Transactional
    public void deleteProduct(Integer id) {
        System.out.println("service - deleteProduct 호출 id = " + id);
        productRepository.deleteById(id);
        System.out.println("상품 삭제 완료 id = " + id);
    }

}
