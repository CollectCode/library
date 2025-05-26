package com.example.storage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {

    USER("사용자"),
    ADMIN("관리자");

    public final String roleName;
}
