package com.example.storage.dto;

import lombok.*;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCRUDRequest {
    private Long id;
    private String title;
    private String author;
    private String publish;
    private String bookImg;
    private LocalDate publishDate;
    private BigInteger price;
}
