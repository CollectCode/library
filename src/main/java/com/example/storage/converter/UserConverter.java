package com.example.storage.converter;

import com.example.storage.domain.UsersEntity;
import com.example.storage.dto.UserCRUDRequest;
import com.example.storage.dto.UserCRUDResponse;
import com.example.storage.dto.UserDto;
import com.example.storage.enums.Roles;
import jakarta.persistence.Converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Component
public class UserConverter implements ConverterImpl<UsersEntity, UserCRUDRequest, UserCRUDResponse> {

    @Override
    public UsersEntity toEntity(UserCRUDRequest request) {
        UserDto dto = request.getUser();
        return UsersEntity.builder()
                .phone(dto.getPhone())
                .dept(dto.getDept())
                .name(dto.getName())
                .info(dto.getInfo())
                .password(dto.getPassword())
                .role(dto.getRole())
                .build();
    }

    @Override
    public UserCRUDResponse toDto(UsersEntity entity) {
        UserDto dto = UserDto.builder()
                .id(entity.getId())
                .info(entity.getInfo())
                .role(entity.getRole())
                .dept(entity.getDept())
                .name(entity.getName())
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
}
