package com.example.storage.dto;

import com.example.storage.enums.Roles;
import lombok.*;

@Getter
@Builder
@ToString
public class UserDto {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String dept;
    private String info;
    private Roles role;
}
