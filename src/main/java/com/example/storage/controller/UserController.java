package com.example.storage.controller;

import com.example.storage.converter.UserConverter;
import com.example.storage.domain.UsersEntity;
import com.example.storage.dto.UserCRUDRequest;
import com.example.storage.dto.UserCRUDResponse;
import com.example.storage.repository.UsersRepository;
import com.example.storage.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    @GetMapping("/{page}")
    public ResponseEntity<Page<UserCRUDResponse>> getAllUsers(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable int page
    ) {
        Page<UserCRUDResponse> responses = service.findAll(page, user);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @GetMapping("/auth/me")
    public ResponseEntity<UserCRUDResponse> getUser(
            @AuthenticationPrincipal UserDetails userDetails
    )   {
        log.info("UserDetails: {}", userDetails);

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserCRUDResponse response = service.getUserByUsername(userDetails);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/logout")
    public ResponseEntity<UserCRUDResponse> logout(
            @AuthenticationPrincipal UserDetails userDetails,
            HttpServletResponse servletResponse
    ) {
        log.info("UserDetails: {}", userDetails);

        if (userDetails == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        UserCRUDResponse response = service.userLogout(userDetails, servletResponse);

        log.info("DeletedUser: {}", SecurityContextHolder.getContext().getAuthentication());

        return ResponseEntity.ok(response);
    }
}
