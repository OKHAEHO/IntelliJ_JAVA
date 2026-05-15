package com.oksusu.springdatajpa.chap01.section02.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceException;
import com.oksusu.springdatajpa.common.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

// @Controller, @Service, @Repository, Component, Configuration -> 빈으로 하면 되는데 왜 이렇게 쓸까? : 직관적으로 표현하기 위해서
// bean 자체가 너무 비대해져서
// 레포 : 데이터 엑세스 역할 디비 기수레 종속되지않도록 스프링 익셉션으로 해서 익셉션이 레포에 고립되게 함 서비스로 넘어가서 종속되는게 아니게
@Repository // 비느로 등록해서 관리해라
public class ProductRepository {

    //안티페턴 : 트렌젝션 단위 관리가 메서드 관리 단위 오버 해드 관리가 된다.
    // 이건 service 에서 해야한다.
    private final EntityManagerFactory emf;

    public ProductRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }
    // 여기까지 ^

    public List<Product> findAllProducts() {
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("Respostory - findAll Porducts : EntitiyManager 생성됨");
            List<Product> products = em.createQuery("SELECT p FROM Product p", Product.class).getResultList();
            System.out.println("Repository - findAllProducts : 조회된 상품 수 : " + products.size());
            return products;
        } catch (PersistenceException e) {
            e.printStackTrace();
        } finally {
            em.close();
            System.out.println("Repository - findAllProducts : EntitiyManager 종료됨");
        }

        return null;
    }

    public Product findProductById(Integer productId) {
        EntityManager em = emf.createEntityManager();

        try {
            System.out.println("repository : findProductById : EntityManager 생성됨 id = " + productId);
            Product product = em.find(Product.class, productId);
            System.out.println("조회된 product" + product.getProductName());
            return product;
        } catch (PersistenceException e) {
            e.printStackTrace();
            System.out.println("error " + e.getMessage());

        } finally {
            em.close();
            System.out.println("findId 종료");
        }

        return null;
    }

}
