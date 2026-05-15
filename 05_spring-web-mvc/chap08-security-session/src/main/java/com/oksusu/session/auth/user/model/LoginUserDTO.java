package com.oksusu.session.auth.user.model;

import com.oksusu.session.common.UserRole;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginUserDTO {
    private int userCode;

    private String userId;
    private String userName;
    private String password;
    private UserRole userRole;

    public LoginUserDTO() {
    }

    public LoginUserDTO(int userCode, String userId, String userName, String password, UserRole userRole) {
        this.userCode = userCode;
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    // n개의 권한을 줄 수 있기 떄문에 custom 함
    public List<String> getUserRole() {
        if (this.userRole.getValue().length() > 0) {
            return Arrays.asList(this.userRole.getValue().split(","));
        }
        return new ArrayList<>();
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
}
