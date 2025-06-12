package com.example.storage.dto;

import com.example.storage.enums.Loan;
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
    private Loan status;
    private BigInteger price;
}
