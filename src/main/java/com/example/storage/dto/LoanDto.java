package com.example.storage.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class LoanDto {
    private BookDto book;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private String status;
}
