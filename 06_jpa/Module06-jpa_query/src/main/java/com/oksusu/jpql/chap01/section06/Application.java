package com.oksusu.jpql.chap01.section06;

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

        String jpql =  "SELECT c FROM Course c";
        TypedQuery<Course> query = em.createQuery(jpql, Course.class);
        query.setFirstResult(0);
        query.setMaxResults(4);

        List<Course> values = query.getResultList();
        values.forEach(System.out::println);

        em.close();
        emf.close();
    }
}
