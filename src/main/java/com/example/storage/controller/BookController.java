package com.example.storage.controller;

import com.example.storage.converter.BookConverter;
import com.example.storage.converter.UserConverter;
import com.example.storage.domain.BookEntity;
import com.example.storage.domain.UsersEntity;
import com.example.storage.dto.BookCRUDRequest;
import com.example.storage.dto.BookCRUDResponse;
import com.example.storage.dto.UserCRUDRequest;
import com.example.storage.dto.UserCRUDResponse;
import com.example.storage.repository.BookRepository;
import com.example.storage.repository.UsersRepository;
import com.example.storage.service.BookService;
import com.example.storage.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.AbstractController;

@RestController
@RequestMapping("/api/book")
public class BookController extends AbsController<
        BookCRUDRequest,
        BookCRUDResponse,
        BookEntity,
        Integer,
        BookConverter,
        BookRepository,
        BookService> {
    // 책 ID를 통한 대출 현황을 조회
    
    // 책 등록 요청
    
    // 책 제거 요청

    // 책 수정 요청

    // 책 대출 요청

    // 책 반납 요청

    // 책 제목으로 조회

    // 책 저자로 조회

    // 책 출판사로 조회
}
