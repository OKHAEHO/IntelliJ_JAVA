package com.oksusu.jpql.chap01.section07;

import com.oksusu.jpql.chap01.model.Course;
import jakarta.persistence.*;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-lecture");
        EntityManager em = emf.createEntityManager();

        String sql = "SELECT u.username AS student_name, c.title AS course_title, " +
                " CONCAT(u.username, ' (', r.role_name, ')') AS instructor_name " +
                " FROM enrollments e " +
                " JOIN users u ON e.user_id = u.user_id " +
                " JOIN courses c ON e.course_id = c.course_id" +
                " JOIN roles r ON u.role_id = r.role_id" +
                " WHERE u.user_id = 6716";

        Query query = em.createNativeQuery(sql);
        List<Object[]> values = query.getResultList();

        for (Object[] row : values) {
            String studentName = (String) row[0];
            String courseTitle = (String) row[1];
            String instructorName = (String) row[2];
            System.out.println("Student Name: " + studentName);
            System.out.println("Course Title: " + courseTitle);
            System.out.println("Instructor Name: " + instructorName);
            System.out.println();
        }

        em.close();
        emf.close();
    }
}
