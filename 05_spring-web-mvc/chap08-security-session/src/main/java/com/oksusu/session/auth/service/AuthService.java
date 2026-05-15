package com.oksusu.session.auth.service;

import com.oksusu.session.auth.model.AuthDetails;
import com.oksusu.session.auth.user.model.LoginUserDTO;
import com.oksusu.session.auth.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService implements UserDetailsService {

    private final UserService userService;

    @Autowired
    public AuthService(UserService userService) {
        this.userService = userService;
    }

    @Override // userProvider 가 호출해줘 약속하는 부분
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginUserDTO login = userService.findByUserId(username);

        if (Objects.isNull(login)) {
            throw new UsernameNotFoundException("회원 정보가 존재하지 않습니다");
        }
        return new AuthDetails(login);
    }
}
