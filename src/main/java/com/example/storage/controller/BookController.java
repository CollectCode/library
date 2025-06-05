package com.example.storage.controller;

import com.example.storage.converter.BookConverter;
import com.example.storage.domain.BookEntity;
import com.example.storage.dto.*;
import com.example.storage.repository.BookRepository;
import com.example.storage.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController extends AbsController<
        BookCRUDRequest,
        BookCRUDResponse,
        BookEntity,
        Long,
        BookConverter,
        BookRepository,
        BookService> {

    public BookController(BookService service) {
        super(service);
    }

    // 책 제목으로 조회
    @GetMapping("/title/{bookTitle}")
    public ResponseEntity<List<BookCRUDResponse>> getListByTitle(
            @PathVariable String bookTitle
    )    {
        List<BookCRUDResponse> responses = service.getListByTitle(bookTitle);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    // 책 저자로 조회
    @GetMapping("/author/{author}")
    public ResponseEntity<List<BookCRUDResponse>> getListByAuthor(
            @PathVariable String author
    )    {
        List<BookCRUDResponse> responses = service.getListByAuthor(author);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    // 책 출판사로 조회
    @GetMapping("/publish/{publish}")
    public ResponseEntity<List<BookCRUDResponse>> getListByPublish(
            @PathVariable String publish
    )    {
        List<BookCRUDResponse> responses = service.getListByPublish(publish);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
