package com.oksusu.jpql.chap01.section05;

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

        // 수업id와 제목, 횟수만 들어있는 CourseDTO
        String jpql = "SELECT new com.oksusu.jpql.chap01.section05.CourseDTO(c.courseId, c.title, COUNT(1))" +
                " FROM Course c JOIN c.lessons l GROUP BY c.courseId, c.title" +
                " HAVING count(1) > :cnt";

        TypedQuery<CourseDTO> query = em.createQuery(jpql, CourseDTO.class);
        query.setParameter("cnt", 5); // 수업이 5개 이상인 것만 출력

        List<CourseDTO> values = query.getResultList();
        values.forEach(System.out::println);

        em.close();
        emf.close();
    }
}
