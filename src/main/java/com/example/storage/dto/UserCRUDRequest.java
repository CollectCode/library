package com.example.storage.dto;

import com.example.storage.enums.Roles;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserCRUDRequest {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String dept;
    private String info;
    private Roles role;
}
