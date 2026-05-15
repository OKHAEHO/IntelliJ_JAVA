package com.oksusu.jpql.chap01.section02;

import com.oksusu.jpql.chap01.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        // EntityManager가 있는 Factory를 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        // EntityManager 생성
        EntityManager em = emf.createEntityManager();

        // Course를 c로 하고 c를 전부 출력하는데 price가 300 이상인 것만.
        String jpql = "SELECT c FROM Course c WHERE c.price >= 300";
        // jpql의 query를 만들어서 돌리고 나오는 결과를 List<Course> courses 에 저장한다.
        List<Course> courses = em.createQuery(jpql, Course.class).getResultList();
        System.out.println("===단일 테이블 조회 ===");
        // course를 순회하며 title과 price를 출력한다.
        courses.forEach(course -> System.out.println(course.getTitle() + " - " + course.getPrice()));

        System.out.println("===다중 테이블 조회 ===");
        // Course를 c로 하고 c를 전부 출력하는데 price가 300 이상인 것만.
        // Course class안의 List lessons을 l로 하고 그 300이상인 c와 그 안의 l을 모두 출력한다.
        String joinQuery = "SELECT c FROM Course c join c.lessons l WHERE c.price >= 300";
        courses = em.createQuery(joinQuery, Course.class).getResultList();

        for (Course course : courses) {
            System.out.println(course.getTitle() + " - " + course.getPrice());
            course.getLessons().forEach(System.out::println);
            System.out.println();
            System.out.println();
        }
        em.close();
        emf.close();
    }
}
