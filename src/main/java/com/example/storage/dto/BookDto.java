package com.example.storage.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;
import java.time.LocalDate;

@Getter
@Builder
public class BookDto {
    private Long id;
    private String title;
    private String author;
    private String publish;
    private LocalDate publishDate;
    private BigInteger price;
}
