package com.example.storage.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Loan {
    LOAN_ABLE("대출가능"),
    LOAN_DISABLE("대출 불가능");

    private final String description;
}
