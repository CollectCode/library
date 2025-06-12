package com.example.storage.dto;

import com.example.storage.enums.Return;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanCRUDResponse {
    private Long loanId;
    private Long userId;
    private Long bookId;
    private String username;
    private String bookTitle;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LocalDate returnExpireDate;
    private Return status;
}
