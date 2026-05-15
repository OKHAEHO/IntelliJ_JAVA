package com.oksusu.session.auth.model;

import com.oksusu.session.auth.user.model.LoginUserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


// 만들어놓은 클래스일 뿐이지만 userDetail 이 되도록 UserDetails 상속(implements)받기
public class AuthDetails implements UserDetails {


    private LoginUserDTO loginUserDTO;

    public AuthDetails() {
    }

    public AuthDetails(LoginUserDTO loginUserDTO) {
        this.loginUserDTO = loginUserDTO;
    }

    public LoginUserDTO getLoginUserDTO() {
        return loginUserDTO;
    }

    public void setLoginUserDTO(LoginUserDTO loginUserDTO) {
        this.loginUserDTO = loginUserDTO;
    }
//사용자정보를 받아와서 넣어줘야하기 떄문에

    // 사용자의 권한 정보를 반환하는 메서드
    @Override // <?extends GrantedAuthority> 이 권한은 n개를 가질 수 있따.
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        loginUserDTO.getUserRole().forEach(role -> grantedAuthorities.add(() -> role));
        return grantedAuthorities;
    }

    @Override // 사용자의 비밀번호를 반환하는 메서드
    public String getPassword() {
        return loginUserDTO.getPassword();
    }

    @Override // 사용자의 아이디를 반환하는 메서드
    public String getUsername() {
        return "";
    }

    // 계정 만료 여부를 표현하는 메서드
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있는 여부를 확이하는 메서드
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 탈퇴 계정을 표현하는 메서드
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 비활성화를 표현하는 메서드
    @Override
    public boolean isEnabled() {
        return true;
    }

}
