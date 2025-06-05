package com.example.storage.service;

import com.example.storage.converter.UserConverter;
import com.example.storage.domain.UsersEntity;
import com.example.storage.dto.UserCRUDRequest;
import com.example.storage.dto.UserCRUDResponse;
import com.example.storage.dto.UserDto;
import com.example.storage.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class UsersService extends AbsService<
        UsersRepository,
        UserCRUDRequest,
        UserCRUDResponse,
        UsersEntity,
        Long,
        UserConverter>{

    public UsersService(UsersRepository repository, UserConverter converter) {
        super(repository, converter);
    }

    @Override
    public UserCRUDResponse update(UserCRUDRequest request) {
        if(request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        UsersEntity entity = repository.findByUsername(request.getUser().getUsername()).orElse(null);
        if(entity == null) {
            throw new IllegalArgumentException("User not found");
        }
        entity.update(request);
        UsersEntity updatedEntity = repository.save(entity);
        return converter.toDto(updatedEntity);
    }

    @Override
    public UserCRUDResponse delete(UserCRUDRequest request) {
        if(request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        UsersEntity entity = repository.findByUsername(request.getUser().getUsername()).orElse(null);
        if(entity == null) {
            throw new IllegalArgumentException("User not found");
        }
        repository.delete(entity);
        return converter.toDto(entity);
    }
}
