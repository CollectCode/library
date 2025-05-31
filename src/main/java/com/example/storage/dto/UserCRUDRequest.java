package com.example.storage.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserCRUDRequest {
    private UserDto user;
}
