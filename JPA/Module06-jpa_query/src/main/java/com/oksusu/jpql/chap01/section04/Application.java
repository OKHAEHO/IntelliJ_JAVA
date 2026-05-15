package com.oksusu.jpql.chap01.section04;

import com.oksusu.jpql.chap01.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        EntityManager em = emf.createEntityManager();

        String jpql = "SELECT c FROM Course c WHERE c.title Like :title";
        TypedQuery<Course> query = em.createQuery(jpql, Course.class);
        /*
        * 파라미터 바인딩
        * - 동적 값 처리 : 쿼리에 변수(:param)을 사용해 안전하게 값을 주입
        * - 객체 중심 : 속설명 (price)에 직접 바인딩
        * */
        query.setParameter("title", "%알고리즘%");
        List<Course> courses = query.getResultList();
        courses.forEach(System.out::println);

        em.close();
        emf.close();
    }
}
