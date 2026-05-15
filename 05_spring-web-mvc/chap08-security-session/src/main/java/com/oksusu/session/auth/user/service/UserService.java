package com.oksusu.session.auth.user.service;

import com.oksusu.session.auth.user.model.LoginUserDTO;
import com.oksusu.session.auth.user.model.SignUpDTO;
import com.oksusu.session.auth.user.model.User;
import com.oksusu.session.auth.user.repository.UserRepository;
import com.oksusu.session.common.UserRole;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 아이디랑 비번을 받잖아 그 비번을 인코딩해서 저장해야하기 떄문에 사용

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Integer regist(SignUpDTO signUpDTO) {

        if(userRepository.existsByUserId(signUpDTO.getUserId())) {
            return null;
        }
        try{
            User user = new User();
            user.setUserId(signUpDTO.getUserId());
            user.setUserName(signUpDTO.getUserName());
            user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
            user.setUserRole(UserRole.valueOf(signUpDTO.getRole()));

            User savedUser = userRepository.save(user);
            return savedUser.getUserCode();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public LoginUserDTO findByUserId(String userId) {
        Optional<User> user = userRepository.findByUserId(userId);

        return userOptional.map(user -> )
    }
}
