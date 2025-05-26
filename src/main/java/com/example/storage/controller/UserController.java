package com.example.storage.controller;

import com.example.storage.converter.UserConverter;
import com.example.storage.domain.UsersEntity;
import com.example.storage.dto.UserCRUDRequest;
import com.example.storage.dto.UserCRUDResponse;
import com.example.storage.dto.UserDto;
import com.example.storage.repository.UsersRepository;
import com.example.storage.service.UsersService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController extends AbsController<
        UserCRUDRequest,
        UserCRUDResponse,
        UsersEntity,
        String,
        UserConverter,
        UsersRepository,
        UsersService> {
    public UserController(UsersService service) {
        super(service);
    }

//    private final UsersService usersService;

//    // 사용자 등록 요청
//    @PostMapping("/add")
//    public ResponseEntity<UserAddUpdateResponse> addUser(@RequestBody UserAddUpdateRequest request) {
//        UserAddUpdateResponse userResponse = usersService.addUpdateUser(request.getUser(), request.getPassword());
//        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
//    }
//
//    // 사용자 갱신 요청
//    @PutMapping("/update")
//    public ResponseEntity<UserAddUpdateResponse> updateUser(@RequestBody UserAddUpdateRequest request)    {
//        UserAddUpdateResponse userResponse = usersService.addUpdateUser(request.getUser(), request.getPassword());
//        return new ResponseEntity<>(userResponse, HttpStatus.OK);
//    }
//
//    // 사용자 탈퇴 요청
//    @DeleteMapping("/remove")
//    public ResponseEntity<String> removeUser(@RequestBody UserRemoveRequest request)  {
//        usersService.removeUser(request.getUserId());
//        return new ResponseEntity<>("유저를 제거하였습니다!", HttpStatus.OK);
//    }
}
