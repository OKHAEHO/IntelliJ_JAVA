package com.oksusu.springdatajpa.chap05;

import com.oksusu.springdatajpa.common.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * 1. 트랜잭션 격리 수준
 * - 여러 트랜잭션이 "동시에" 실행될 때, 서로의 작업 내용에 얼마나 노출될지를 결정하는 안전 장치
 *
 * 2. 왜 필요한가?
 * - 동시성과 데이터 일관성은 트레이드오프 관계이다.
 * - 낮은 격리 수준 : 동시성은 높지만 빠름, 데이터 일관성이 깨질 수 있따.
 * - 높은 격리 수준 : 데이터 일관성은 높지만 동시성이 낮아짐
 *
 * 3. spring 에서 설정 방법
 * - @Transactional 어노테이션의 isolation 속성으로 격리 수준을 설정할 수 있따.
 *
 * 4. 격리 수준의 종류
 * - Isolation.READ_UNCOMMITEED : 가장 낮은 격리 수준, 다른 트랜잭션이 커밋되지 않은 데이트러를 읽을 수 있따.(Dirty read 발생 가능)
 * - ISolation.READ_COMMITTED : 커밋된 데이터만 읽을 수 있다(Non - Repeatable Read 발생 가능)
 * - ISolation.REPEATABLE_READ : 동일한 데이터를 반복해서 읽을 떄 , 같은 결과가 나옴(Phantom Read 발생 가능)
 * - Isolation.SERIALIZABLE : 가장 높은 격리 수준, 트랜잭션 간의 충돌을 완전히 방지함(성능 저하 초래)
 * */
@Service("chap05-productService")
public class ProductService {

    //Isolation의 Default는 DB 기본 격리 수준을 따르겠다는 의미.
    @Transactional(isolation = Isolation.DEFAULT, readOnly = true)
    public Product findProductDefault(Integer productId) {
        return null;
    }

    /*
     * Isolation.Read_Committed: dirty Read 방지
     * = 다른 트랜잭션이 커밋한 데이터만 읽는다
     * */
    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly = true)
    public List<Product> findProducts(Integer productId) {
        return null;
    }

    /*
     * Isolation.REPEATABLE_READ : dirty Read, non-repeatable Read 방지
     * = 트랜잭션이 시작된 이후로 동일한 데이터를 반복해서 읽을 떄 같은 결과가 나옴
     * */
    @Transactional(isolation = Isolation.REPEATABLE_READ, readOnly = true)
    public List<Product> findProducts(Integer productId, Integer page, Integer pageSize) {
        return null;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE, readOnly = true)
    public void performSerializableTransaction() {
        // 트랜잭션 간의 충돌을 완전히 방지하는 가장 높은 격리 수준
        // 성능 저하가 발생할 수 있으므로, 필요한 경우에만 사용
    }
}
