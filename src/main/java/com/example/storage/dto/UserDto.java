package com.example.storage.dto;

import com.example.storage.enums.Roles;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDto {
    private String id;
    private String name;
    private String phone;
    private String dept;
    private String info;
    private Roles role;
    private String password;
}
