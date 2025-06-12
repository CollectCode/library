package com.example.storage.dto;

import com.example.storage.enums.Loan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookCRUDResponse {
    private Long id;
    private String title;
    private String author;
    private String publish;
    private String bookImg;
    private LocalDate publishDate;
    private Loan status;
    private BigInteger price;
}
