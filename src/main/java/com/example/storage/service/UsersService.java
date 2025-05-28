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
}
