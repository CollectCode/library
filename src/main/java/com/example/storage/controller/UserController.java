package com.example.storage.controller;

import com.example.storage.converter.UserConverter;
import com.example.storage.domain.UsersEntity;
import com.example.storage.dto.UserCRUDRequest;
import com.example.storage.dto.UserCRUDResponse;
import com.example.storage.repository.UsersRepository;
import com.example.storage.service.UsersService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController extends AbsController<
        UserCRUDRequest,
        UserCRUDResponse,
        UsersEntity,
        Long,
        UserConverter,
        UsersRepository,
        UsersService> {
    public UserController(UsersService service) {
        super(service);
    }
}
