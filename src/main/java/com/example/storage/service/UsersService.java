package com.example.storage.service;

import com.example.storage.converter.UserConverter;
import com.example.storage.domain.UsersEntity;
import com.example.storage.dto.UserCRUDRequest;
import com.example.storage.dto.UserCRUDResponse;
import com.example.storage.dto.UserDto;
import com.example.storage.repository.UsersRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UsersService extends AbsService<
        UsersRepository,
        UserCRUDRequest,
        UserCRUDResponse,
        UsersEntity,
        Long,
        UserConverter>{

    private final JwtService jwtService;

    public UsersService(UsersRepository repository, UserConverter converter, JwtService jwtService) {
        super(repository, converter);
        this.jwtService = jwtService;
    }

    public Page<UserCRUDResponse> findAll(int page, UserDetails user) {
        log.info("asd : {}", user.getAuthorities().stream().toList());
        if(!user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))   {
           throw new IllegalArgumentException("You do not have permission to access this resource");
        }

        Pageable pageable = PageRequest.of(page, 10);
        Page<UsersEntity> users = repository.findAll(pageable);
        return converter.toDtoList(users);
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

    public UserCRUDResponse getUserByUsername(UserDetails user) {
        if(user == null) {
            throw new UsernameNotFoundException("user not exists");
        }

        UsersEntity entity = repository.findByUsername(user.getUsername()).orElse(null);

        log.info("get UserEntity : {}", entity);

        if(entity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return converter.toDto(entity);
    }

    public UserCRUDResponse userLogout(UserDetails user, HttpServletResponse response) {
        if(user == null) {
            throw new UsernameNotFoundException("user not exists");
        }
        UsersEntity entity = repository.findByUsername(user.getUsername()).orElse(null);
        if(entity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Map<String, ResponseCookie> cookies = jwtService.logout(entity);

        for (ResponseCookie cookie : cookies.values()) {
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }

        SecurityContextHolder.clearContext();

        return converter.toDto(entity);
    }
}
