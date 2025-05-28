package com.example.storage.dto;

import com.example.storage.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class UserDto {
    private Long id;
    private String name;
    private String phone;
    private String dept;
    private String info;
    private Roles role;
    private String password;
}
