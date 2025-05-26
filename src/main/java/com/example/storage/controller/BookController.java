package com.example.storage.controller;

import com.example.storage.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {
    private final BookService bookService;

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
