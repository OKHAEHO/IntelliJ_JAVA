package com.oksusu.jpql.chap01.section01;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        EntityManager em = emf.createEntityManager();

        String sql = "SELECT * FROM courses WHERE price >= 300";
        List<Object[]> resultList = em.createNativeQuery(sql).getResultList();
        System.out.println(resultList.size());

        for (Object[] row : resultList) {
            System.out.println("courseId = " + row[0] + ", title = " + row[1]);
            System.out.println("----------------------------------------");
        }
        em.close();
        emf.close();
    }
}
