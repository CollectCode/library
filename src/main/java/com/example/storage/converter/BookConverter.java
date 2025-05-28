package com.example.storage.converter;

import com.example.storage.domain.BookEntity;
import com.example.storage.dto.BookCRUDRequest;
import com.example.storage.dto.BookCRUDResponse;
import com.example.storage.dto.BookDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookConverter implements ConverterImpl<BookEntity, BookCRUDRequest, BookCRUDResponse> {

    @Override
    public BookEntity toEntity(BookCRUDRequest request) {
        BookDto dto = request.getBook();
        return BookEntity.builder()
                .bookImg(dto.getBookImg())
                .price(dto.getPrice())
                .author(dto.getAuthor())
                .publish(dto.getPublish())
                .publishDate(dto.getPublishDate())
                .title(dto.getTitle())
                .build();
    }

    @Override
    public BookCRUDResponse toDto(BookEntity entity) {
        return null;
    }

    @Override
    public List<BookCRUDResponse> toDtoList(List<BookEntity> entities) {
        return List.of();
    }
}
