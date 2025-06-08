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
        UserDto dto = request.getUser();
        return UsersEntity.builder()
                .phone(dto.getPhone())
                .dept(dto.getDept())
                .username(dto.getUsername())
                .info(dto.getInfo())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(dto.getRole())
                .build();
    }

    @Override
    public UserCRUDResponse toDto(UsersEntity entity) {
        log.info("Get user in converter : {}", entity);
        UserDto dto = UserDto.builder()
                .id(entity.getUserId())
                .info(entity.getInfo())
                .role(entity.getRole())
                .dept(entity.getDept())
                .username(entity.getUsername())
                .phone(entity.getPhone())
                .build();
        return UserCRUDResponse.builder()
                .user(dto)
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
