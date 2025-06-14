package com.example.storage.controller;

import com.example.storage.converter.BookConverter;
import com.example.storage.domain.BookEntity;
import com.example.storage.dto.*;
import com.example.storage.repository.BookRepository;
import com.example.storage.service.BookService;
import org.springframework.data.domain.Page;
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
    
    // 모든 책 조회
    @GetMapping("/all/{page}")
    public ResponseEntity<Page<BookCRUDResponse>> getAll(
            @PathVariable int page
    )  {
        Page<BookCRUDResponse> responses = service.findAll(page, 10);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
    
    // 책 ID로 조회
    @GetMapping("/{bookId}")
    public ResponseEntity<BookCRUDResponse> getById(
            @PathVariable("bookId") Long bookId
    )  {
        BookCRUDResponse response = service.findById(bookId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 책 제목으로 조회
    @GetMapping("/title/{bookTitle}")
    public ResponseEntity<Page<BookCRUDResponse>> getListByTitle(
            @PathVariable String bookTitle
    )    {
        Page<BookCRUDResponse> responses = service.getListByTitle(bookTitle);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    // 책 저자로 조회
    @GetMapping("/author/{author}")
    public ResponseEntity<Page<BookCRUDResponse>> getListByAuthor(
            @PathVariable String author
    )    {
        Page<BookCRUDResponse> responses = service.getListByAuthor(author);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    // 책 출판사로 조회
    @GetMapping("/publisher/{publish}")
    public ResponseEntity<Page<BookCRUDResponse>> getListByPublish(
            @PathVariable String publish
    )    {
        Page<BookCRUDResponse> responses = service.getListByPublish(publish);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
