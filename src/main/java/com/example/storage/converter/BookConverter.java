package com.example.storage.converter;

import com.example.storage.domain.BookEntity;
import com.example.storage.dto.BookCRUDRequest;
import com.example.storage.dto.BookCRUDResponse;
import com.example.storage.dto.BookDto;
import com.example.storage.enums.Loan;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookConverter implements ConverterImpl<BookEntity, BookCRUDRequest, BookCRUDResponse> {

    @Override
    public BookEntity toEntity(BookCRUDRequest request) {
        return BookEntity.builder()
                .bookImg(request.getBookImg())
                .price(request.getPrice())
                .author(request.getAuthor())
                .publish(request.getPublish())
                .publishDate(request.getPublishDate())
                .title(request.getTitle())
                .status(Loan.LOAN_ABLE)
                .build();
    }

    @Override
    public BookCRUDResponse toDto(BookEntity entity) {
        return BookCRUDResponse.builder()
                .id(entity.getBookId())
                .bookImg(entity.getBookImg())
                .author(entity.getAuthor())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .publish(entity.getPublish())
                .publishDate(entity.getPublishDate())
                .status(entity.getStatus())
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
