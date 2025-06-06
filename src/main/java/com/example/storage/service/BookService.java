package com.example.storage.service;

import com.example.storage.converter.BookConverter;
import com.example.storage.domain.BookEntity;
import com.example.storage.dto.BookCRUDRequest;
import com.example.storage.dto.BookCRUDResponse;
import com.example.storage.repository.BookRepository;
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
        BookEntity book = repository.findById(request.getBook().getId()).orElse(null);
        if(book == null) {
            throw new IllegalArgumentException("book cannot be null");
        }
        book.update(request);
        BookEntity updatedBooks = repository.save(book);
        return converter.toDto(updatedBooks);
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
