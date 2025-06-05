package com.example.storage.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class LoanReturnRequest {
    private Long bookId;
    private LocalDate returnDate;
}
