package com.oksusu.jpql.chap04.service;


import org.example.springdatajpa.chap04.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("chap03-productService")
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        System.out.println("ProductService(Chap03) 생성: ProductRepository 주입됨");
        this.productRepository = productRepository;
    }

}