package com.example.storage.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCRUDResponse {
    private UserDto user;
}
