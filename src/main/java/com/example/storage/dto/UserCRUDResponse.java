package com.example.storage.dto;

import com.example.storage.enums.Roles;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCRUDResponse {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String dept;
    private String info;
    private Roles role;
}
