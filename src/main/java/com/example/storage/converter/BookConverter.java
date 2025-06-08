package com.example.storage.converter;

import com.example.storage.domain.BookEntity;
import com.example.storage.dto.BookCRUDRequest;
import com.example.storage.dto.BookCRUDResponse;
import com.example.storage.dto.BookDto;
import org.springframework.data.domain.Page;
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
        BookDto dto = BookDto.builder()
                .id(entity.getBookId())
                .bookImg(entity.getBookImg())
                .author(entity.getAuthor())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .publish(entity.getPublish())
                .publishDate(entity.getPublishDate())
                .build();
        return BookCRUDResponse.builder()
                .book(dto)
                .build();
    }

    @Override
    public List<BookCRUDResponse> toDtoList(List<BookEntity> entities) {
        return entities
                .stream()
                .map(this::toDto)
                .toList();
    }

    public Page<BookCRUDResponse> toDtoList(Page<BookEntity> entities) {
        return entities.map(this::toDto);
    }
}
