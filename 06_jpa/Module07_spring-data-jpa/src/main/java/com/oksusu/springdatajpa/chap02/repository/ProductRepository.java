package com.oksusu.springdatajpa.chap02.repository;

import com.oksusu.springdatajpa.common.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("chap02-repository") // 안써도 상관ㅇ없지만 관례사ㅇ 쓴다.
public interface ProductRepository extends JpaRepository<Product, Integer> {
    // 기본 제공 기능 JpaRepository
    // save 더티체킹을 통해 상태 변경이나 추가를 감지해서
    // findById -> em.find(Product.class, id) :
    // findAll -> em.createQuery("select p from Product p", Product.class).getResultList()
    // delete(T) -> em.delete(entity)
    // paging 관리

}
