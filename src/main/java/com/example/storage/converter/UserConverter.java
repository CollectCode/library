package com.example.storage.converter;

import com.example.storage.domain.UsersEntity;
import com.example.storage.dto.UserCRUDRequest;
import com.example.storage.dto.UserCRUDResponse;
import com.example.storage.dto.UserDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class UserConverter implements ConverterImpl<UsersEntity, UserCRUDRequest, UserCRUDResponse> {

    @Override
    public UsersEntity toEntity(UserCRUDRequest request) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return UsersEntity.builder()
                .phone(request.getPhone())
                .dept(request.getDept())
                .username(request.getUsername())
                .info(request.getInfo())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
    }

    @Override
    public UserCRUDResponse toDto(UsersEntity entity) {
        return UserCRUDResponse.builder()
                .id(entity.getUserId())
                .info(entity.getInfo())
                .role(entity.getRole())
                .dept(entity.getDept())
                .username(entity.getUsername())
                .phone(entity.getPhone())
                .build();
    }

    @Override
    public List<UserCRUDResponse> toDtoList(List<UsersEntity> entities) {
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    public Page<UserCRUDResponse> toDtoList(Page<UsersEntity> entities) {
        return entities.map(this::toDto);
    }
}
