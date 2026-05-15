package com.oksusu.jpql.chap01.section03;

import com.oksusu.jpql.chap01.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT c FROM Course c WHERE c.price >= 300;";

        String typedJpql = "SELECT c FROM Course c WHERE c.price >= 300";
        /*
        * 타입 쿼리
        * 타입 안정성 : 경과를 제네릭 타입으로 지정하며 컴파일 시점에 타입 오류를 감지할 수 있다.
        * 객체 중심적 접근 : 쿼리 결과를 직접 지정한 엔티티 클래스로 매핑하여 불필요한 형변환 없이 바로 사용할 수 있따.
        * 가독성 향상 : 코드의 가독성이 높아지고 ,의도를 명확히 전달할 수 있따.*/
        TypedQuery<Course> courses = em.createQuery(typedJpql, Course.class);
        courses.getResultList().forEach(System.out::println);

        em.close();
        emf.close();
    }
}
