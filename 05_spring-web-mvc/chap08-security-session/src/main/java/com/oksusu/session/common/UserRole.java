package com.oksusu.session.common;

public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");
    private String value;

    UserRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "UserRole{" +
                "value='" + value + '\'' +
                '}';
    }
}
