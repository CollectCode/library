package com.example.storage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanDto {
    private BookDto book;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private String status;
}
