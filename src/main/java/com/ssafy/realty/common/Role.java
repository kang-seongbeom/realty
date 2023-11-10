package com.ssafy.realty.common;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum Role {
    USER("user"), ADMIN("admin");

    private final String role;

    Role(String value) {
        this.role = value;
    }

    public static Role findByRoleValue(String value){
        return Arrays.stream(Role.values()).filter(r -> r.role.equals(value))
                .findFirst().orElseThrow(() -> new NoSuchElementException("잘못된 유저 타입 입니다."));
    }
}
