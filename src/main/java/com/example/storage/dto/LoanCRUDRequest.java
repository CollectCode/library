package com.example.storage.dto;

import com.example.storage.enums.Return;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanCRUDRequest {
    private Long loanId;
    private Long userId;
    private Long bookId;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LocalDate returnExpireDate;
    private Return status;
}
