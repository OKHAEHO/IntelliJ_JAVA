package com.oksusu.session.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Override // userProvider 가 호출해줘 약속하는 부분
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return null;
    }
}
