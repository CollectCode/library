package com.example.storage.service;

import com.example.storage.converter.BookConverter;
import com.example.storage.domain.BookEntity;
import com.example.storage.dto.BookCRUDRequest;
import com.example.storage.dto.BookCRUDResponse;
import com.example.storage.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService extends AbsService<
        BookRepository,
        BookCRUDRequest,
        BookCRUDResponse,
        BookEntity,
        Long,
        BookConverter> {

    public BookService(BookRepository repository, BookConverter converter) {
        super(repository, converter);
    }

    @Override
    public BookCRUDResponse update(BookCRUDRequest request)    {
        if(request == null) {
            throw new IllegalArgumentException("request cannot be null");
        }
        BookEntity book = repository.findById(request.getId()).orElse(null);
        if(book == null) {
            throw new IllegalArgumentException("book cannot be null");
        }
        book.update(request);
        BookEntity updatedBooks = repository.save(book);
        return converter.toDto(updatedBooks);
    }

    public Page<BookCRUDResponse> findAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BookEntity> books = repository.findAll(pageable);
        return converter.toDtoList(books);
    }

    public BookCRUDResponse findById(Long id) {
        BookEntity book = repository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("book cannot be null");
        });
        return converter.toDto(book);
    }

    public List<BookCRUDResponse> getListByTitle(String title) {
        List<BookEntity> books = repository.findAllByTitle(title);
        return converter.toDtoList(books);
    }

    public List<BookCRUDResponse> getListByAuthor(String author) {
        List<BookEntity> books = repository.findAllByAuthor(author);
        return converter.toDtoList(books);
    }

    public List<BookCRUDResponse> getListByPublish(String publish) {
        List<BookEntity> books = repository.findAllByPublish(publish);
        return converter.toDtoList(books);
    }
}
