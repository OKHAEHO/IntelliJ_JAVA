package com.oksusu.session.auth.user.repository;

import com.oksusu.session.auth.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    // 아이디 확인
    Optional<User> findByUserId(String userId);

    // 중복되는지 확인
    boolean existsByUserId(String userId);


}
